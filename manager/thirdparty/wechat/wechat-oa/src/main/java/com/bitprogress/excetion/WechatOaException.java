package com.bitprogress.excetion;

import com.bitprogress.exception.IExceptionMessage;
import com.bitprogress.exception.WechatException;

/**
 * @author 不会飞的小鹏
 *  微信模块异常
 */
public class WechatOaException extends WechatException {
    public WechatOaException(IExceptionMessage requestException) {
        super(requestException);
    }
}
