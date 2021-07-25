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
    public static final String AUTH_TOKEN_WRONG = "AUTH_TOKEN_WRONG";
    public static final String AUTH_TOKEN_EMPTY = "AUTH_TOKEN_EMPTY";

    public static final String ACCOUNT_HAS_DISABLED = "ACCOUNT_HAS_DISABLED";
    public static final String ACCOUNT_ALREADY_EXIST = "ACCOUNT_ALREADY_EXIST";
    public static final String NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO = "NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO";
    public static final String NOT_ROOT_ONLY_CAN_CHECK_SELF_INFO = "NOT_ROOT_ONLY_CAN_CHECK_SELF_INFO";
    public static final String NOT_ALLOW_ADD_WPX_EXCEPT_ROOT = "NOT_ALLOW_ADD_WPX_EXCEPT_ROOT";
    public static final String MANAGER_NOT_EXIST = "MANAGER_NOT_EXIST";
    public static final String MANAGER_UPDATE_ERROR = "MANAGER_UPDATE_ERROR";
    public static final String MANAGER_DELETE_ERROR = "MANAGER_DELETE_ERROR";
    public static final String MANAGER_SAVE_ERROR = "MANAGER_SAVE_ERROR";
    public static final String ROOT_CANNOT_DELETE = "ROOT_CANNOT_DELETE";
    public static final String NOT_ROOT_CANNOT_DELETE = "NOT_ROOT_CANNOT_DELETE";
    public static final String ROOT_CANNOT_DISABLED = "ROOT_CANNOT_DISABLED";
    public static final String NOT_ROOT_CANNOT_DISABLED = "NOT_ROOT_CANNOT_DISABLED";
    public static final String NOT_ALLOW_SUPERIOR_ROLE = "NOT_ALLOW_SUPERIOR_ROLE";

    //验证码相关

    public static final String AUTH_PICCAPTCHA_WRONG = "AUTH_PICCAPTCHA_WRONG";
    public static final String AUTH_PICCAPTCHA_LOST = "AUTH_PICCAPTCHA_LOST";

    /* 加密解密相关 */

    public static final String RSAUTIL_DECRYPT_ERROR = "RSAUTIL_DECRYPT_ERROR";

    public static final String APPLICATION_NOT_EXIST = "APPLICATION_NOT_EXIST";
    public static final String APPLICATION_UPDATE_ERROR = "APPLICATION_UPDATE_ERROR";
    public static final String APPLICATION_DELETE_ERROR = "APPLICATION_DELETE_ERROR";
    public static final String APPLICATION_SAVE_ERROR = "APPLICATION_SAVE_ERROR";
    public static final String APPLICATIONINFO_NOT_EXIST = "APPLICATIONINFO_NOT_EXIST";
    public static final String APPLICATIONINFO_SAVE_ERROR = "APPLICATIONINFO_SAVE_ERROR";
    public static final String APPLICATIONINFO_UPDATE_ERROR = "APPLICATIONINFO_UPDATE_ERROR";
    public static final String APPLICATIONINFO_DELETE_ERROR = "APPLICATIONINFO_DELETE_ERROR";
    public static final String APPLICATIONTOPIC_NOT_EXIST = "APPLICATIONINFO_DELETE_ERROR";
    public static final String APPLICATIONTOPIC_SAVE_ERROR = "APPLICATIONTOPIC_SAVE_ERROR";
    public static final String APPLICATIONTOPIC_UPDATE_ERROR = "APPLICATIONTOPIC_UPDATE_ERROR";
    public static final String APPLICATIONTOPIC_DELETE_ERROR = "APPLICATIONTOPIC_DELETE_ERROR";

}
