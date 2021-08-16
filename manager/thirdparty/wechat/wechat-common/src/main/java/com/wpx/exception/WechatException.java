package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 * @description： 微信模块异常
 */
public class WechatException extends CommonException {
    public WechatException(IExceptionMessage requestException) {
        super(requestException);
    }
}
