package com.wpx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wpx.constant.CharacterConstants;
import com.wpx.constant.StringConstants;
import com.wpx.exception.CommonException;
import com.wpx.util.StringUtils;
import com.wpx.constant.WechatPayConstants;
import com.wpx.constant.WechatUrl;
import com.wpx.exception.WechatPayExceptionMessage;
import com.wpx.model.apppay.AppPayOrder;
import com.wpx.model.apppay.AppPayUnifiedOrder;
import com.wpx.model.h5pay.H5PayOrder;
import com.wpx.model.h5pay.H5PayUnifiedOrder;
import com.wpx.model.jsapi.JsApiPayOrder;
import com.wpx.model.jsapi.JsApiUnifiedOrder;
import com.wpx.okhttp.util.OkHttpClientUtils;
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
 * @Description: 微信支付统一下单服务类
 */
public class WechatPayUnifiedOrderService {

    /**
     * JSAPI下单
     *
     * @param unifiedOrder
     * @param filePath
     */
    public JsApiPayOrder jsApiUnifiedOrder(JsApiUnifiedOrder unifiedOrder, String filePath) {
        String body = JSON.toJSONString(unifiedOrder);
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        JsApiPayOrder jsApiPayOrder = new JsApiPayOrder();
        String appid = unifiedOrder.getAppid();
        jsApiPayOrder.setAppId(appid);
        String timestamp = String.valueOf(Instant.now().getEpochSecond());
        jsApiPayOrder.setTimeStamp(timestamp);
        jsApiPayOrder.setSignType(WechatPayConstants.SIGN_TYPE);
        String nonceStr = StringUtils.randomStringByUUID();
        jsApiPayOrder.setNonceStr(nonceStr);
        try {
            String result = OkHttpClientUtils.doPost(WechatUrl.JS_API_UNIFIED_URL, body, mediaType);
            JSONObject object = JSON.parseObject(result);
            StringBuilder builder = new StringBuilder();
            object.forEach((key, value) -> builder.append(key).append(StringConstants.EQUAL_SIGN).append(value));
            String packaged = builder.toString();
            jsApiPayOrder.setPackaged(packaged);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(WechatPayExceptionMessage.JSAPI_UNIFIED_ORDER_ERROR);
        }
        try {
            String paySignCode = generatePaySignCode(appid, timestamp, nonceStr, jsApiPayOrder.getPackaged());
            String paySign = paySign(paySignCode, filePath);
            jsApiPayOrder.setPaySign(paySign);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | IOException
                | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new CommonException(WechatPayExceptionMessage.JSAPI_PAY_SIGN_ERROR);
        }
        return jsApiPayOrder;
    }

    /**
     * APP支付统一下单
     */
    public AppPayOrder appPayUnifiedOrder(AppPayUnifiedOrder unifiedOrder, String filePath) {
        String body = JSON.toJSONString(unifiedOrder);
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        AppPayOrder appPayOrder = new AppPayOrder();
        String appid = unifiedOrder.getAppid();
        appPayOrder.setAppid(appid);
        appPayOrder.setPartnerid(unifiedOrder.getMchid());
        String timestamp = String.valueOf(Instant.now().getEpochSecond());
        appPayOrder.setTimestamp(timestamp);
        String nonceStr = StringUtils.randomStringByUUID();
        appPayOrder.setNoncestr(nonceStr);
        appPayOrder.setPackaged(WechatPayConstants.PACKAGE);
        try {
            String result = OkHttpClientUtils.doPost(WechatUrl.APP_PAY_UNIFIED_URL, body, mediaType);
            JSONObject object = JSON.parseObject(result);
            StringBuilder builder = new StringBuilder();
            object.forEach((key, value) -> builder.append(value));
            String prepayid = builder.toString();
            appPayOrder.setPrepayid(prepayid);
        } catch (Exception e) {
            e.printStackTrace();
            throw CommonException.error(WechatPayExceptionMessage.APP_PAY_UNIFIED_ORDER_ERROR);
        }
        try {
            String paySignCode = generatePaySignCode(appid, timestamp, nonceStr, appPayOrder.getPrepayid());
            String paySign = paySign(paySignCode, filePath);
            appPayOrder.setSign(paySign);
        } catch (Exception e) {
            e.printStackTrace();
            throw CommonException.error(WechatPayExceptionMessage.APP_PAY_SIGN_ERROR);
        }
        return appPayOrder;
    }

    /**
     * H5支付统一下单
     */
    public H5PayOrder appPayUnifiedOrder(H5PayUnifiedOrder unifiedOrder) {
        String body = JSON.toJSONString(unifiedOrder);
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        try {
            String result = OkHttpClientUtils.doPost(WechatUrl.H5_PAY_UNIFIED_URL, body, mediaType);
            return JSON.parseObject(result, H5PayOrder.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(WechatPayExceptionMessage.H5_PAY_UNIFIED_ORDER_ERROR);
        }
    }

    /**
     * 拼装签名数据
     *
     * @param values
     */
    public String generatePaySignCode(String... values) {
        StringBuilder builder = new StringBuilder();
        for (String value : values) {
            builder.append(value).append("\\n");
        }
        return builder.toString();
    }

    /**
     * 对支付数据进行签名
     *
     * @param paySignCode
     * @param filePath
     */
    public String paySign(String paySignCode, String filePath) throws InvalidKeyException,
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
    public PrivateKey generatePrivateKey(String filePath) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        StringBuilder str = new StringBuilder();
        String s = br.readLine();
        while (s.charAt(0) != CharacterConstants.UNDERLINE){
            str.append(s).append("\r");
            s = br.readLine();
        }
        BASE64Decoder base64decoder = new BASE64Decoder();
        byte[] b = base64decoder.decodeBuffer(str.toString());

        KeyFactory kf = KeyFactory.getInstance(StringConstants.CIPHER_RSA);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(b);
        return kf.generatePrivate(keySpec);
    }

}
