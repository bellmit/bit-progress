package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/26 20:08
 * @Description 请求异常信息枚举
 */
public enum ExceptionMessage implements IExceptionMessage {

    /**
     * 请求参数错误或者参数为空
     */
    REQUEST_ARGUMENT(1001, ErrorCodes.AUTH_ACCOUNT_PASSWORD_WRONG, MessageCodes.AUTH_ACCOUNT_PASSWORD_WRONG),
    /**
     * 参数类型没有指定
     */
    TYPE_NOT_APPOINT(1002, ErrorCodes.TYPE_NOT_APPOINT, MessageCodes.TYPE_NOT_APPOINT),
    /**
     * 非网关转发
     */
    NON_GATEWAY_FORWARD_ERROR(1003, ErrorCodes.NON_GATEWAY_FORWARD, MessageCodes.NON_GATEWAY_FORWARD),
    /**
     * 非内部访问
     */
    NON_INTERNAL_ACCESS_ERROR(1004, ErrorCodes.NON_INTERNAL_ACCESS, MessageCodes.NON_INTERNAL_ACCESS),
    /**
     * 验证码错误
     */
    AUTH_PICCAPTCHA_WRONG(1005, ErrorCodes.AUTH_PICCAPTCHA_WRONG, MessageCodes.AUTH_PICCAPTCHA_WRONG),
    /**
     * 验证码已失效
     */
    AUTH_PICCAPTCHA_LOST(1006, ErrorCodes.AUTH_PICCAPTCHA_LOST, MessageCodes.AUTH_PICCAPTCHA_LOST),
    /**
     * 解密失败
     */
    RSAUtil_DECRYPT_ERROR(1007, ErrorCodes.RSAUTIL_DECRYPT_ERROR, MessageCodes.RSAUTIL_DECRYPT_ERROR),
    /**
     * 账号密码出错
     */
    AUTH_ACCOUNT_PASSWORD_WRONG(1008, ErrorCodes.AUTH_ACCOUNT_PASSWORD_WRONG, MessageCodes.AUTH_ACCOUNT_PASSWORD_WRONG),
    /**
     * 账号已被禁用
     */
    ACCOUNT_HAS_DISABLED(1009, ErrorCodes.ACCOUNT_HAS_DISABLED, MessageCodes.ACCOUNT_HAS_DISABLED),
    /**
     * 账号已存在
     */
    ACCOUNT_ALREADY_EXIST(1010, ErrorCodes.ACCOUNT_ALREADY_EXIST, MessageCodes.ACCOUNT_ALREADY_EXIST),

    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 错误码
     */
    private String error;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 获取异常状态码
     */
    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getError() {
        return this.error;
    }

    /**
     * 获取异常信息
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ExceptionMessage(Integer code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

}
