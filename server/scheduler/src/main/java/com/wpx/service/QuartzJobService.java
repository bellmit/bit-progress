package com.wpx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.exception.ScheduleException;
import com.wpx.exception.ScheduleExceptionMessage;
import com.wpx.model.BooleanVO;
import com.wpx.model.quartzjob.QuartzJob;
import com.wpx.model.quartzjob.envm.TriggerStateEnum;
import com.wpx.model.quartzjob.envm.TriggerType;
import com.wpx.model.quartzjob.pojo.*;
import com.wpx.quartz.factory.QuartzFactory;
import com.wpx.util.CollectionUtils;
import com.wpx.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author 不会飞的小鹏
 *  定时任务服务类
 */
@Service
@Slf4j
public class QuartzJobService {

    @Autowired
    private QuartzFactory quartzFactory;

    /**
     * 添加定时任务
     *
     * @param addDTO
     */
    public QuartzJobVO createJob(QuartzJobAddDTO addDTO) {
        QuartzJob quartzJob = new QuartzJob();
        BeanUtils.copyProperties(addDTO, quartzJob);
        try {
            JobKey jobKey = quartzFactory.createQuartzJob(quartzJob);
            return getJobByKey(jobKey);
        } catch (Exception e) {
            log.error("quartzJobCreate error: ", e);
            throw new ScheduleException(ScheduleExceptionMessage.QUARTZJOB_CREATE_EXCEPTION);
        }
    }

    /**
     * 获取定时任务信息
     *
     * @param getDTO
     */
    public QuartzJobVO getJob(QuartzJobGetDTO getDTO) {
        String jobKeyName = getDTO.getJobKeyName();
        String jobKeyGroup = getDTO.getJobKeyGroup();
        JobKey jobKey = JobKey.jobKey(jobKeyName, jobKeyGroup);
        return getJobByKey(jobKey);
    }

    /**
     * 创建多个定时任务
     *
     * @param listAddDTO
     */
    public List<QuartzJobVO> createJobList(QuartzJobListAddDTO listAddDTO) {
        List<QuartzJobAddDTO> quartzAddList = listAddDTO.getQuartzAddList();
        if (CollectionUtils.isEmpty(quartzAddList)) {
            return new ArrayList<>();
        }
        return CollectionUtils.conversionList(quartzAddList, this::createJob);
    }

    /**
     * 移除定时任务
     *
     * @param deleteDTO
     * @throws SchedulerException
     */
    public void deleteJob(QuartzJobDeleteDTO deleteDTO) {
        String triggerKeyName = deleteDTO.getTriggerKeyName();
        String triggerKeyGroup = deleteDTO.getTriggerKeyGroup();
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerKeyName, triggerKeyGroup);
        String jobKeyName = deleteDTO.getJobKeyName();
        String jobKeyGroup = deleteDTO.getJobKeyGroup();
        JobKey jobKey = JobKey.jobKey(jobKeyName, jobKeyGroup);
        try {
            quartzFactory.removeJob(triggerKey, jobKey);
        } catch (SchedulerException e) {
            log.error("quartzJobRemove error ", e);
            throw new ScheduleException(ScheduleExceptionMessage.QUARTZJOB_REMOVE_EXCEPTION);
        }
    }

    /**
     * 分页获取任务
     *
     * @param queryDTO
     * @param page
     * @throws SchedulerException
     */
    public IPage<QuartzJobVO> pageJob(QuartzJobQueryDTO queryDTO, Page page) {
        long current = page.getCurrent();
        long size = page.getSize();
        long offset = (current - 1) * size;

        String groupName = queryDTO.getGroupName();
        TriggerType type = queryDTO.getTriggerType();
        TriggerStateEnum stateEnum = queryDTO.getTriggerState();
        Trigger.TriggerState triggerState = Trigger.TriggerState.valueOf(stateEnum.name());
        List<QuartzJobVO> jobList = listJob(triggerState, type, groupName);
        List<QuartzJobVO> records = limitJobList(jobList, offset, size);
        page.setRecords(records);
        page.setTotal(jobList.size());
        return page;


    }

    /**
     * 获取所有定时任务
     *
     * @throws SchedulerException
     */
    public List<QuartzJobVO> listJob(Trigger.TriggerState triggerState, TriggerType type, String groupName) {
        try {
            List<QuartzJobVO> jobList = StringUtils.isEmpty(groupName) ? listJob() : listJob(groupName);
            if (CollectionUtils.isEmpty(jobList)) {
                return new ArrayList<>();
            }
            return filterJob(jobList, triggerState, type);
        } catch (SchedulerException e) {
            log.error("quartzJobQueryList error ", e);
            throw new ScheduleException(ScheduleExceptionMessage.QUARTZJOB_QUERY_EXCEPTION);
        }
    }

    /**
     * 根据筛选条件对定时任务进行筛选
     *
     * @param jobList
     * @param state
     * @param type
     * @return
     */
    private List<QuartzJobVO> filterJob(List<QuartzJobVO> jobList, Trigger.TriggerState state, TriggerType type) {
        if (Objects.nonNull(state) && Objects.nonNull(type)) {
            return CollectionUtils.filterList(jobList, job -> filterQuartzJob(job, state, type));
        }
        if (Objects.nonNull(state)) {
            return CollectionUtils.filterList(jobList, job -> filterQuartzJob(job, state));
        }
        if (Objects.nonNull(type)) {
            return CollectionUtils.filterList(jobList, job -> filterQuartzJob(job, type));
        }
        return jobList;
    }

    /**
     * 筛选符合类型和状态的任务
     *
     * @param job
     * @param state
     */
    private boolean filterQuartzJob(QuartzJobVO job, Trigger.TriggerState state) {
        TriggerStateEnum stateEnum = job.getTriggerState();
        return Objects.equals(state, Trigger.TriggerState.valueOf(stateEnum.name()));
    }

    /**
     * 筛选符合类型和状态的任务
     *
     * @param job
     * @param type
     */
    private boolean filterQuartzJob(QuartzJobVO job, TriggerType type) {
        return Objects.equals(type, job.getTriggerType());
    }

    /**
     * 筛选符合类型和状态的任务
     *
     * @param job
     * @param state
     * @param type
     */
    private boolean filterQuartzJob(QuartzJobVO job, Trigger.TriggerState state, TriggerType type) {
        TriggerStateEnum stateEnum = job.getTriggerState();
        return Objects.equals(state, Trigger.TriggerState.valueOf(stateEnum.name()))
                && Objects.equals(type, job.getTriggerType());
    }

    /**
     * 获取所有定时任务
     *
     * @throws SchedulerException
     */
    public List<QuartzJobVO> listJob() {
        Set<JobKey> jobKeys = listJobKeys();
        return CollectionUtils.conversionList(jobKeys, this::getJobByKey);
    }

    /**
     * 获取分组的定时任务
     *
     * @throws SchedulerException
     */
    public List<QuartzJobVO> listJob(String groupName) throws SchedulerException {
         Set<JobKey> jobKeys = StringUtils.isEmpty(groupName) ? listJobKeys() : listJobKeys(groupName);
        return CollectionUtils.conversionList(jobKeys, this::getJobByKey);
    }

    /**
     * 查询所有jobKey
     *
     * @throws SchedulerException
     */
    public Set<JobKey> listJobKeys() {
        try {
            return quartzFactory.getJobKeys(GroupMatcher.anyGroup());
        } catch (SchedulerException e) {
            log.error("jobKeyQuery error ", e);
            throw new ScheduleException(ScheduleExceptionMessage.JOBKEY_QUERY_EXCEPTION);
        }
    }

    /**
     * 根据分组查询所有jobKey
     *
     * @throws SchedulerException
     */
    public Set<JobKey> listJobKeys(String groupName) {
        try {
            return quartzFactory.getJobKeys(GroupMatcher.groupEquals(groupName));
        } catch (SchedulerException e) {
            log.error("jobKeyQuery error ", e);
            throw new ScheduleException(ScheduleExceptionMessage.JOBKEY_QUERY_EXCEPTION);
        }

    }

    /**
     * 根据jobKey查询job相关信息
     *
     * @param jobKey
     */
    private QuartzJobVO getJobByKey(JobKey jobKey) {
        try {
            String jobKeyName = jobKey.getName();
            String jobKeyGroup = jobKey.getGroup();
            JobDetail jobDetail = quartzFactory.getJobDetail(jobKey);
            List<? extends Trigger> triggers = quartzFactory.getTriggersOfJob(jobKey);
            Trigger trigger = triggers.get(0);
            Date nextFireTime = trigger.getNextFireTime();
            Date previousFireTime = trigger.getPreviousFireTime();
            int priority = trigger.getPriority();
            int misfireInstruction = trigger.getMisfireInstruction();
            TriggerKey triggerKey = trigger.getKey();
            Trigger.TriggerState triggerState = quartzFactory.getTriggerState(triggerKey);

            String triggerKeyName = triggerKey.getName();
            String triggerKeyGroup = triggerKey.getGroup();

            QuartzJob quartzJob = (QuartzJob) jobDetail.getJobDataMap().get("quartzJob");
            QuartzJobVO jobVO = new QuartzJobVO();
            BeanUtils.copyProperties(quartzJob, jobVO);

            // 时间转换
            ZoneId zoneId = ZoneId.systemDefault();
            if (Objects.nonNull(previousFireTime)) {
                Instant preInstant = previousFireTime.toInstant();
                LocalDateTime preTime = preInstant.atZone(zoneId).toLocalDateTime();
                jobVO.setPreviousFireTime(preTime);
            }
            if (Objects.nonNull(nextFireTime)) {
                Instant nextInstant = nextFireTime.toInstant();
                LocalDateTime nextTime = nextInstant.atZone(zoneId).toLocalDateTime();
                jobVO.setNextFireTime(nextTime);
            }

            jobVO.setPriority(priority);
            jobVO.setMisfireInstruction(misfireInstruction);
            jobVO.setJobKeyName(jobKeyName);
            jobVO.setJobKeyGroup(jobKeyGroup);
            jobVO.setTriggerKeyName(triggerKeyName);
            jobVO.setTriggerKeyGroup(triggerKeyGroup);
            jobVO.setTriggerState(TriggerStateEnum.valueOf(triggerState.name()));
            return jobVO;
        }catch (SchedulerException e) {
            log.error("quartzJobQuery error ", e);
            throw new ScheduleException(ScheduleExceptionMessage.QUARTZJOB_QUERY_EXCEPTION);
        }
    }

    /**
     * 对任务列表进行
     *
     * @param jobList
     * @param offset
     * @param limit
     */
    public List<QuartzJobVO> limitJobList(List<QuartzJobVO> jobList, long offset, long limit) {
        if (CollectionUtils.isEmpty(jobList)) {
            return new ArrayList<>();
        }
        return CollectionUtils.limitList(jobList, offset, limit);
    }

    /**
     * 暂停任务
     *
     * @param triggerDTO
     */
    public QuartzJobVO pauseTrigger(QuartzJobTriggerDTO triggerDTO) {
        String name = triggerDTO.getTriggerKeyName();
        String group = triggerDTO.getTriggerKeyGroup();
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        try {
            quartzFactory.pauseTrigger(triggerKey);
            return getJobByTriggerKey(triggerKey);
        } catch (SchedulerException e) {
            log.error("triggerPause error ", e);
            throw new ScheduleException(ScheduleExceptionMessage.TRIGGER_PAUSE_EXCEPTION);
        }
    }

    /**
     * 恢复任务
     *
     * @param dto
     */
    public QuartzJobVO rescheduleJob(QuartzJobTriggerDTO dto) {
        String name = dto.getTriggerKeyName();
        String group = dto.getTriggerKeyGroup();
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        try {
            quartzFactory.rescheduleJob(triggerKey);
            return getJobByTriggerKey(triggerKey);
        } catch (SchedulerException e) {
            log.error("triggerReschedule error ", e);
            throw new ScheduleException(ScheduleExceptionMessage.TRIGGER_RESCHEDULE_EXCEPTION);
        }
    }

    /**
     * 根据triggerKey返回Job
     *
     * @param triggerKey
     */
    private QuartzJobVO getJobByTriggerKey(TriggerKey triggerKey) throws SchedulerException {
        Trigger trigger = quartzFactory.getTrigger(triggerKey);
        JobKey jobKey = trigger.getJobKey();
        return getJobByKey(jobKey);
    }

    /**
     * 检查是否有同名任务
     * jobGroupName分组为空的情况下，采用默认分组 QuartzJob.class.getName()
     *
     * @param existsCheckDTO
     * @throws SchedulerException
     */
    public BooleanVO checkExists(QuartzJobExistsCheckDTO existsCheckDTO) {
        String jobName = existsCheckDTO.getJobName();
        String jobGroupName = existsCheckDTO.getJobGroupName();
        if (StringUtils.isEmpty(jobGroupName)) {
            jobGroupName = QuartzJob.class.getName();
        }
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        Boolean result;
        try {
            return BooleanVO.result(quartzFactory.checkExists(jobKey));
        } catch (SchedulerException e) {
            log.error("jobCheckExists error ", e);
            throw new ScheduleException(ScheduleExceptionMessage.QUARTZJOB_CHECK_EXISTS_EXCEPTION);
        }
    }

    /**
     * 获取分组信息
     *
     * @return 所有分组的名称
     * @throws SchedulerException
     */
    public List<String> listJobGroup() {
        try {
            return quartzFactory.getJobGroupNames();
        } catch (SchedulerException e) {
            log.error("jobGroupQuery error ", e);
            throw new ScheduleException(ScheduleExceptionMessage.JOBGROUP_QUERY_EXCEPTION);
        }
    }

}
