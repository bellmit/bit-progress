package com.wpx.exception;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 定时任务异常
 */
public class ScheduleException extends CommonException {

    public ScheduleException(IExceptionMessage requestException) {
        super(requestException);
    }

}
