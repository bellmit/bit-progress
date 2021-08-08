package com.wpx.exception;

/**
 * @Author: 不会飞的小鹏
 * @Description: 基础模块异常
 */
public class BaseException extends CommonException {
    public BaseException(IExceptionMessage requestException) {
        super(requestException);
    }
}
