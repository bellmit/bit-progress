package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 * 微信支付异常，继承自 WechatException
 */
public class WechatPayException extends WechatException {

    public WechatPayException(IExceptionMessage requestException) {
        super(requestException);
    }

}
