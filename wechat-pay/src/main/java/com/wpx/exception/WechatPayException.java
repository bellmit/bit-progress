package com.wpx.exception;

import com.wpx.exception.CustomizeException;
import com.wpx.exception.IExceptionMessage;

/**
 * @author 不会飞的小鹏
 * @Description: 微信支付异常，继承自 CustomizeException
 */
public class WechatPayException extends CustomizeException {

    public WechatPayException(IExceptionMessage requestException) {
        super(requestException);
    }

}
