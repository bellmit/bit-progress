package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 * @Description: 错误码
 */
public class ErrorCodes {

    public static final String REQUEST_ARGUMENT = "REQUEST_ARGUMENT";
    public static final String AUTH_ACCOUNT_PASSWORD_WRONG = "AUTH_ACCOUNT_PASSWORD_WRONG";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String TYPE_NOT_APPOINT = "TYPE_NOT_APPOINT";
    public static final String NON_GATEWAY_FORWARD = "NON_GATEWAY_FORWARD";
    public static final String NON_INTERNAL_ACCESS = "NON_INTERNAL_ACCESS";

    public static final String ACCOUNT_HAS_DISABLED = "ACCOUNT_HAS_DISABLED";
    public static final String ACCOUNT_ALREADY_EXIST = "ACCOUNT_ALREADY_EXIST";

    //验证码相关

    public static final String AUTH_PICCAPTCHA_WRONG = "AUTH_PICCAPTCHA_WRONG";
    public static final String AUTH_PICCAPTCHA_LOST = "AUTH_PICCAPTCHA_LOST";

    /* 加密解密相关 */

    public static final String RSAUTIL_DECRYPT_ERROR = "RSAUTIL_DECRYPT_ERROR";

}
