package com.wpx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wpx.common.constant.StringConstants;
import com.wpx.common.exception.CustomizeException;
import com.wpx.common.exception.ExceptionMessage;
import com.wpx.common.util.StringUtils;
import com.wpx.constant.WeChatPayConstants;
import com.wpx.constant.WeChatUrl;
import com.wpx.model.jsapi.JsApiPayOrder;
import com.wpx.model.jsapi.JsApiUnifiedOrder;
import com.wpx.util.HttpClientUtils;
import okhttp3.MediaType;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;

/**
 * @author 不会飞的小鹏
 * create on 2021/7/19 1:04
 * @Description: 支付服务类
 */
public class WeChatPayService {

    /**
     * JSAPI下单
     *
     * @param unifiedOrder
     * @param filePath
     */
    public static JsApiPayOrder jsApiUnifiedOrder(JsApiUnifiedOrder unifiedOrder, String filePath) {
        String body = JSON.toJSONString(unifiedOrder);
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        JsApiPayOrder jsApiPayOrder = new JsApiPayOrder();
        jsApiPayOrder.setAppId(unifiedOrder.getAppid());
        jsApiPayOrder.setTimeStamp(String.valueOf(Instant.now().getEpochSecond()));
        jsApiPayOrder.setSignType(WeChatPayConstants.SIGN_TYPE);
        jsApiPayOrder.setNonceStr(StringUtils.randomString(32));
        try {
            String result = HttpClientUtils.doPost(WeChatUrl.JS_API_UNIFIED_URL, body, mediaType);
            JSONObject object = JSON.parseObject(result);
            StringBuilder builder = new StringBuilder();
            object.forEach((key, value) -> builder.append(key).append(StringConstants.EQUAL_SIGN).append(value));
            jsApiPayOrder.setPackaged(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomizeException(ExceptionMessage.JSAPI_UNIFIED_ORDER_ERROR);
        }
        try {
            String paySignCode = generatePaySignCode(jsApiPayOrder);
            String paySign = paySign(paySignCode, filePath);
            jsApiPayOrder.setPaySign(paySign);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | IOException
                | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new CustomizeException(ExceptionMessage.JSAPI_PAY_SIGN_ERROR);
        }
        return jsApiPayOrder;
    }

    /**
     * 拼装签名数据
     *
     * @param jsApiPayOrder
     */
    public static String generatePaySignCode(JsApiPayOrder jsApiPayOrder) {
        StringBuilder builder = new StringBuilder();
        return builder.append(jsApiPayOrder.getAppId())
                .append("\\n")
                .append(jsApiPayOrder.getTimeStamp())
                .append("\\n")
                .append(jsApiPayOrder.getNonceStr())
                .append("\\n")
                .append(jsApiPayOrder.getPackaged())
                .append("\\n")
                .toString();
    }

    /**
     * 对支付数据进行签名
     *
     * @param paySignCode
     * @param filePath
     */
    public static String paySign(String paySignCode, String filePath) throws InvalidKeyException,
            NoSuchAlgorithmException, SignatureException,
            IOException, InvalidKeySpecException {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        PrivateKey privateKey = generatePrivateKey(filePath);
        privateSignature.initSign(privateKey);
        privateSignature.update(paySignCode.getBytes(StandardCharsets.UTF_8));
        byte[] signature = privateSignature.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    /**
     * 将pem文件转换为私钥对象
     *
     * @param filePath
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static PrivateKey generatePrivateKey(String filePath) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder str = new StringBuilder();
        String s = br.readLine();
        while (s.charAt(0) != StringConstants.UNDERLINE){
            str.append(s).append("\r");
            s = br.readLine();
        }
        BASE64Decoder base64decoder = new BASE64Decoder();
        byte[] b = base64decoder.decodeBuffer(str.toString());

        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b);
        return kf.generatePrivate(keySpec);
    }

}
