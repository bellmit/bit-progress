package com.wpx.quartz.factory;

import com.wpx.exception.ScheduleException;
import com.wpx.exception.ScheduleExceptionMessage;
import com.wpx.model.quartzjob.QuartzJob;
import com.wpx.model.quartzjob.envm.TriggerType;
import com.wpx.quartz.job.CustomJob;
import com.wpx.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.utils.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: 不会飞的小鹏
 * @Description: Quartz
 */
@Component
@Slf4j
public class QuartzFactory {

    @Autowired
    private Scheduler scheduler;

    public JobKey createQuartzJob(QuartzJob job) throws SchedulerException {
        String name = job.getJobName();
        if (Objects.isNull(name)) {
            return null;
        }

        String groupName = job.getGroupName();
        if (StringUtils.isEmpty(groupName)) {
            groupName = job.getClass().getName();
        }
        JobKey jobKey = new JobKey(name, groupName);

        TriggerKey triggerKey = new TriggerKey(name, groupName);
        Trigger tri = scheduler.getTrigger(triggerKey);
        if (Objects.nonNull(tri)) {
            log.info("quartzJob exists, jobName [{}]", name);
            return jobKey;
        } else {
            log.info("create quartz trigger，name {}", name);
            TriggerType type = job.getTriggerType();
            long millis = System.currentTimeMillis();
            switch (type) {
                case SIMPLE: {
                    Long duration = job.getDuration();
                    Long startAtTime;
                    if (Objects.nonNull(duration)) {
                        startAtTime = millis + duration;
                    } else {
                        startAtTime = millis;
                    }
                    JobDetail jobDetail = JobBuilder.newJob(CustomJob.class).withIdentity(jobKey)
                            .build();
                    jobDetail.getJobDataMap().put("quartzJob", job);
                    Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                            .startAt(new Date(startAtTime)).build();
                    scheduler.scheduleJob(jobDetail, trigger);

                    return jobKey;
                }
                case CRON: {
                    String cronExpression = job.getCronExpression();
                    if (Objects.nonNull(cronExpression)) {
                        JobDetail jobDetail = JobBuilder.newJob(CustomJob.class).withIdentity(jobKey).build();
                        jobDetail.getJobDataMap().put("quartzJob", job);
                        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression)
                                .withMisfireHandlingInstructionDoNothing();
                        Trigger scheduleTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                                .withSchedule(scheduleBuilder).startAt(new Date(millis)).build();
                        scheduler.scheduleJob(jobDetail, scheduleTrigger);

                        return jobKey;
                    } else {
                        return null;
                    }
                }
                default: {
                    return null;
                }
            }
        }
    }

    /**
     * 获取任务信息
     *
     * @param jobKey
     * @throws SchedulerException
     */
    public JobDetail getJobDetail(JobKey jobKey) throws SchedulerException {
        return scheduler.getJobDetail(jobKey);
    }

    /**
     * 获取触发器信息
     *
     * @param jobKey
     * @throws SchedulerException
     */
    public List<? extends Trigger> getTriggersOfJob(JobKey jobKey) throws SchedulerException {
        return scheduler.getTriggersOfJob(jobKey);
    }

    /**
     * 获取触发器状态
     *
     * @param triggerKey
     * @throws SchedulerException
     */
    public Trigger.TriggerState getTriggerState(TriggerKey triggerKey) throws SchedulerException {
        return scheduler.getTriggerState(triggerKey);
    }

    /**
     * 重试job
     *
     * @param job
     */
    public void reTrySimpleJob(QuartzJob job) {
        try {
            job.setJobName(job.getJobName() + "10");
            String name = job.getJobName();

            log.info("重试任务，名称 {}", name);

            TriggerKey triggerKey = new TriggerKey(name, job.getClass().getName());
            Trigger tri = scheduler.getTrigger(triggerKey);
            if (Objects.isNull(tri)) {
                TriggerType type = job.getTriggerType();
                if (type.equals(TriggerType.SIMPLE)) {
                    String groupName = job.getClass().getName();
                    //递增重试，每 5 * retryTime ^ 2
                    Long startAtTime = System.currentTimeMillis() + new Double(5000 * Math.pow(job.getRetryTime(), 2)).longValue();
                    JobDetail jobDetail = JobBuilder.newJob(CustomJob.class).withIdentity(name, groupName).build();
                    jobDetail.getJobDataMap().put("quartzJob", job);
                    Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, groupName)
                            .startAt(new Date(startAtTime)).build();
                    scheduler.scheduleJob(jobDetail, trigger);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("retry error: ", e);
        }
    }

    /**
     * 移除任务
     *
     * @param jobName
     * @param triggerName
     */
    public void removeJob(String jobName, String triggerName) {
        try {
            String triggerGroupName = QuartzJob.class.getName();
            String jobGroupName = QuartzJob.class.getName();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            removeJob(triggerKey, jobKey);
        } catch (Exception e) {
            log.error("removeJob error: ", e);
            log.error("移除 job[{}] 发生异常", jobName);
        }
    }

    /**
     * 移除任务
     *
     * @param triggerKey
     * @param jobKey
     * @throws SchedulerException
     */
    public void removeJob(TriggerKey triggerKey, JobKey jobKey) throws SchedulerException {
        //停止触发器
        scheduler.pauseTrigger(triggerKey);
        //移除触发器
        scheduler.unscheduleJob(triggerKey);
        //删除任务
        scheduler.deleteJob(jobKey);
    }

    /**
     * 是否有相同名称的job
     *
     * @param jobName
     */
    public Boolean hasSameNameJob(String jobName) {
        try {
            String jobGroupName = QuartzJob.class.getName();
            JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
            return null == jobDetail ? false : true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 停止触发器
     *
     * @param triggerKey
     * @throws SchedulerException
     */
    public void pauseTrigger(TriggerKey triggerKey) throws SchedulerException {
        scheduler.pauseTrigger(triggerKey);
    }

    /**
     * 恢复触发器
     *
     * @param triggerKey
     * @throws SchedulerException
     */
    public void rescheduleJob(TriggerKey triggerKey) throws SchedulerException {
        Trigger trigger = scheduler.getTrigger(triggerKey);
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 查询所有jobKey
     *
     * @param anyGroup
     */
    public Set<JobKey> getJobKeys(GroupMatcher<JobKey> anyGroup) throws SchedulerException {
        return scheduler.getJobKeys(anyGroup);
    }

    /**
     * 检查任务是否已存在
     *
     * @param jobKey
     * @throws SchedulerException
     */
    public Boolean checkExists(JobKey jobKey) throws SchedulerException {
        return scheduler.checkExists(jobKey);
    }

    /**
     * 查询触发器
     *
     * @param triggerKey
     * @throws SchedulerException
     */
    public Trigger getTrigger(TriggerKey triggerKey) throws SchedulerException {
        return scheduler.getTrigger(triggerKey);
    }

    /**
     * 查询任务分组
     */
    public List<String> getJobGroupNames() throws SchedulerException {
        return scheduler.getJobGroupNames();
    }

}
