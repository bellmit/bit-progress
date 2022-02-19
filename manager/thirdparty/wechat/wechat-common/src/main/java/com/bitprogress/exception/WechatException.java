package com.bitprogress.exception;

/**
 * @author 不会飞的小鹏
 *  微信模块异常
 */
public class WechatException extends CommonException {
    public WechatException(IExceptionMessage requestException) {
        super(requestException);
    }
}
