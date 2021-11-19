package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 * 基础模块异常
 */
public class BaseException extends CommonException {
    public BaseException(IExceptionMessage requestException) {
        super(requestException);
    }
}
