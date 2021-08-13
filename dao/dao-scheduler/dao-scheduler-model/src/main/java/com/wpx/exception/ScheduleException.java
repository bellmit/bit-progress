package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 *  定时任务异常
 */
public class ScheduleException extends CommonException {

    public ScheduleException(IExceptionMessage requestException) {
        super(requestException);
    }

}
