package com.wpx.excetion;

import com.wpx.exception.IExceptionMessage;
import com.wpx.exception.WechatException;

/**
 * @author 不会飞的小鹏
 *  微信模块异常
 */
public class WechatOaException extends WechatException {
    public WechatOaException(IExceptionMessage requestException) {
        super(requestException);
    }
}
