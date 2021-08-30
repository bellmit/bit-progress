package com.wpx.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * AliSms 短信验证码工具类
 *
 * @author 不会飞的小鹏
 */
public class AliSmsUtils {

    /* 产品域名, 以下常量基本不变 */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String VERSION = "2017-05-25";
    private static final String SMS_ACTION = "SendSms";
    private static final String REGION_ID = "cn-hangzhou";

    /**
     * 发送短信验证码
     *
     * @param phone 手机号码
     * @param signName  签名
     * @param templateCode  模板
     */
    public String sendSms(String phone, String signName, String templateCode, String accessKeyId,
                          String accessKeySecret) {
        String code = NumberUtils.randomNumbers(6);
        sendSms(phone, code, signName, templateCode, accessKeyId, accessKeySecret);
        return code;
    }

    /**
     * 发送境外短信验证码
     *
     * @param phone 手机号码
     * @param overseaSignName  境外签名
     * @param overseaTemplateCode  境外模板
     */
    public String sendOverseaAliSms(String phone, String overseaSignName, String overseaTemplateCode, String accessKeyId,
                                    String accessKeySecret) {
        String code = NumberUtils.randomNumbers(6);
        sendSms(phone, code, overseaSignName, overseaTemplateCode, accessKeyId, accessKeySecret);
        return code;
    }

    /**
     * 发送短信验证码
     *
     * @param phone 手机号码
     * @param code  随机数
     */
    private Boolean sendSms(String phone, String code, String signName, String templateCode, String accessKeyId,
                            String accessKeySecret) {
        boolean send = false;
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("code", code);
        String jsonString = JsonUtils.serializeObject(paramMap);

        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(DOMAIN);
        request.setVersion(VERSION);
        request.setAction(SMS_ACTION);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("TemplateParam", jsonString);
        try {
            // 发送短信验证码
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            send = true;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return send;
    }

}
