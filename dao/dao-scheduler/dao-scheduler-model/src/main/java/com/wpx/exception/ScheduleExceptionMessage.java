package com.wpx.exception;

import static com.wpx.exception.ScheduleErrorCodes.*;
import static com.wpx.exception.ScheduleMessageCodes.*;

/**
 * @author 不会飞的小鹏
 *  定时任务异常
 */
public enum ScheduleExceptionMessage implements IExceptionMessage {

    /**
     * 定时任务创建异常
     */
    QUARTZJOB_CREATE_EXCEPTION(2001, QUARTZJOB_CREATE_EXCEPTION_CODE, QUARTZJOB_CREATE_EXCEPTION_MESSAGE),

    /**
     * 定时任务查询异常
     */
    QUARTZJOB_QUERY_EXCEPTION(2002, QUARTZJOB_QUERY_EXCEPTION_CODE, QUARTZJOB_QUERY_EXCEPTION_MESSAGE),

    /**
     * 定时任务移除异常
     */
    QUARTZJOB_REMOVE_EXCEPTION(2003, QUARTZJOB_REMOVE_EXCEPTION_CODE, QUARTZJOB_REMOVE_EXCEPTION_MESSAGE),

    /**
     * 定时任务查重异常
     */
    QUARTZJOB_CHECK_EXISTS_EXCEPTION(2004, QUARTZJOB_CHECK_EXISTS_EXCEPTION_CODE, QUARTZJOB_CHECK_EXISTS_EXCEPTION_MESSAGE),

    /**
     * jobKey查询异常
     */
    JOBKEY_QUERY_EXCEPTION(2005, JOBKEY_QUERY_EXCEPTION_CODE, JOBKEY_QUERY_EXCEPTION_MESSAGE),

    /**
     * 触发器暂停异常
     */
    TRIGGER_PAUSE_EXCEPTION(2006, TRIGGER_PAUSE_EXCEPTION_CODE, TRIGGER_PAUSE_EXCEPTION_MESSAGE),

    /**
     * 触发器恢复异常
     */
    TRIGGER_RESCHEDULE_EXCEPTION(2007, TRIGGER_RESCHEDULE_EXCEPTION_CODE, TRIGGER_RESCHEDULE_EXCEPTION_MESSAGE),

    /**
     * 查询任务分组异常
     */
    JOBGROUP_QUERY_EXCEPTION(2008, JOBGROUP_QUERY_EXCEPTION_CODE, JOBGROUP_QUERY_EXCEPTION_MESSAGE),

    /**
     * 定时任务名称不能为空
     */
    QUARTZJOB_NAME_EMPTY_EXCEPTION(2009, QUARTZJOB_NAME_EMPTY_EXCEPTION_CODE, QUARTZJOB_NAME_EMPTY_EXCEPTION_MESSAGE),

    /**
     * 定时任务cron表达式不能为空
     */
    QUARTZJOB_CRON_EMPTY_EXCEPTION(2010, QUARTZJOB_CRON_EMPTY_EXCEPTION_CODE, QUARTZJOB_CRON_EMPTY_EXCEPTION_MESSAGE),

    /**
     * 定时任务类型不能为空
     */
    QUARTZJOB_TYPE_EMPTY_EXCEPTION(2010, QUARTZJOB_TYPE_EMPTY_EXCEPTION_CODE, QUARTZJOB_TYPE_EMPTY_EXCEPTION_MESSAGE),

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
