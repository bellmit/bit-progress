package com.wpx.exception;

import com.wpx.exception.IExceptionMessage;

/**
 * @author 不会飞的小鹏
 * @Description: 微信支付异常信息 继承自{@link com.wpx.exception.ExceptionMessage}
 */
public enum WechatPayExceptionMessage implements IExceptionMessage {

    /**
     * JSAPI支付统一下单失败
     */
    JSAPI_UNIFIED_ORDER_ERROR(4001, WechatPayErrorCode.JSAPI_UNIFIED_ORDER_ERROR, WechatPayMessageCode.JSAPI_UNIFIED_ORDER_ERROR),
    /**
     * JSAPI支付信息签名失败
     */
    JSAPI_PAY_SIGN_ERROR(4002, WechatPayErrorCode.JSAPI_PAY_SIGN_ERROR, WechatPayMessageCode.JSAPI_PAY_SIGN_ERROR),
    /**
     * APP支付统一下单失败
     */
    APP_PAY_UNIFIED_ORDER_ERROR(4003, WechatPayErrorCode.APP_PAY_UNIFIED_ORDER_ERROR, WechatPayMessageCode.APP_PAY_UNIFIED_ORDER_ERROR),
    /**
     * H5支付统一下单失败
     */
    H5_PAY_UNIFIED_ORDER_ERROR(4004, WechatPayErrorCode.H5_PAY_UNIFIED_ORDER_ERROR, WechatPayMessageCode.H5_PAY_UNIFIED_ORDER_ERROR),
    /**
     * APP支付信息签名失败
     */
    APP_PAY_SIGN_ERROR(4005, WechatPayErrorCode.JSAPI_PAY_SIGN_ERROR, WechatPayMessageCode.JSAPI_PAY_SIGN_ERROR),
    /**
     * 微信支付订单查询失败
     */
    TRANSACTION_QUERY_ERROR(4006, WechatPayErrorCode.TRANSACTION_QUERY_ERROR, WechatPayMessageCode.TRANSACTION_QUERY_ERROR),

    ;

    private Integer code;

    private String error;

    private String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getError() {
        return this.error;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    WechatPayExceptionMessage(Integer code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

}
