package com.wpx.exception;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 定时任务异常信息
 */
public class ScheduleMessageCodes {

    public static final String QUARTZJOB_CREATE_MESSAGE = "定时任务创建异常";
    public static final String QUARTZJOB_QUERY_MESSAGE = "定时任务查询异常";
    public static final String QUARTZJOB_REMOVE_MESSAGE = "定时任务移除异常";
    public static final String QUARTZJOB_CHECK_EXISTS_MESSAGE = "定时任务查重异常";
    public static final String JOBKEY_QUERY_MESSAGE = "jobKey查询异常";
    public static final String TRIGGER_PAUSE_MESSAGE = "触发器暂停异常";
    public static final String TRIGGER_RESCHEDULE_MESSAGE = "触发器恢复异常";
    public static final String JOBGROUP_QUERY_MESSAGE = "查询任务分组异常";

}
