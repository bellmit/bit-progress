package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 * @Description: 微信支付异常，继承自 CustomizeException
 */
public class WechatPayException extends CommonException {

    public WechatPayException(IExceptionMessage requestException) {
        super(requestException);
    }

}
