package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 */
public class MessageCodes {

    public static final String REQUEST_ARGUMENT = "请求参数错误或者参数为空";
    public static final String AUTH_ACCOUNT_PASSWORD_WRONG = "账号或密码错误";
    public static final String INTERNAL_SERVER_ERROR = "server_internal";
    public static final String TYPE_NOT_APPOINT = "参数类型没有指定";
    public static final String NON_GATEWAY_FORWARD = "非网关转发";
    public static final String NON_INTERNAL_ACCESS = "非内部访问";
    //验证码相关
    public static final String AUTH_PICCAPTCHA_WRONG = "验证码错误";
    public static final String AUTH_PICCAPTCHA_LOST = "验证码已失效";
    public static final String ACCOUNT_HAS_DISABLED = "账号已被禁用";
    public static final String ACCOUNT_ALREADY_EXIST = "账号已经存在";
    //加密解密相关
    public static final String RSAUTIL_DECRYPT_ERROR = "解密失败";

}
