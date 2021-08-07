package com.wpx.exception;

import static com.wpx.exception.ScheduleErrorCodes.*;
import static com.wpx.exception.ScheduleMessageCodes.*;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 定时任务异常
 */
public enum ScheduleExceptionMessage implements IExceptionMessage {

    /**
     * 定时任务创建异常
     */
    QUARTZJOB_CREATE_ERROR(2001, QUARTZJOB_CREATE_CODE, QUARTZJOB_CREATE_MESSAGE),

    /**
     * 定时任务查询异常
     */
    QUARTZJOB_QUERY_ERROR(2002, QUARTZJOB_QUERY_CODE, QUARTZJOB_QUERY_MESSAGE),

    /**
     * 定时任务移除异常
     */
    QUARTZJOB_REMOVE_ERROR(2003, QUARTZJOB_REMOVE_CODE, QUARTZJOB_REMOVE_MESSAGE),

    /**
     * 定时任务查重异常
     */
    QUARTZJOB_CHECK_EXISTS_ERROR(2004, QUARTZJOB_CHECK_EXISTS_CODE, QUARTZJOB_CHECK_EXISTS_MESSAGE),

    /**
     * jobKey查询异常
     */
    JOBKEY_QUERY_ERROR(2005, JOBKEY_QUERY_CODE, JOBKEY_QUERY_MESSAGE),

    /**
     * 触发器暂停异常
     */
    TRIGGER_PAUSE_ERROR(2006, TRIGGER_PAUSE_CODE, TRIGGER_PAUSE_MESSAGE),

    /**
     * 触发器恢复异常
     */
    TRIGGER_RESCHEDULE_ERROR(2007, TRIGGER_RESCHEDULE_CODE, TRIGGER_RESCHEDULE_MESSAGE),

    /**
     * 查询任务分组异常
     */
    JOBGROUP_QUERY_ERROR(2008, JOBGROUP_QUERY_CODE, JOBGROUP_QUERY_MESSAGE),

    ;

    private Integer code;

    private String error;

    private String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getError() {
        return this.error;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ScheduleExceptionMessage(Integer code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

}
