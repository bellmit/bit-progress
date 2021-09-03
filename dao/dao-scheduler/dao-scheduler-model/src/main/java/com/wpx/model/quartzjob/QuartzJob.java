package com.wpx.model.quartzjob;

import com.wpx.model.quartzjob.envm.TriggerType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 不会飞的小鹏
 * @Description: 定时任务
 **/
@Data
public class QuartzJob implements Serializable {

    private static final long serialVersionUID = 6575630849578006711L;

    private String jobName;

    private TriggerType triggerType;

    /**
     * 重试次数
     */
    private Integer retryTime;

    private String cronExpression;

    private Long duration;

    /**
     * bean名
     */
    private String beanName;

    /**
     * 方法名
     */
    private String methodName;

    private String argsList;

    private String argsClassList;

    /**
     * 定时器分组，例：main-cms-kitty-cron，main-cms-kitty-simple
     */
    private String groupName;

    /**
     * 调用的服务
     */
    private String applicationName;

    /**
     * 定时器的服务调用地址
     */
    private String serverUrl;

    /**
     * 服务调用的token
     */
    private String restToken;

    /*public QuartzJob(String jobName, TriggerType triggerType, String cronExpression, Long duration, String beanName, String methodName) {
        this.jobName = jobName;
        this.triggerType = triggerType;
        this.cronExpression = cronExpression;
        this.duration = duration;
        this.beanName = beanName;
        this.methodName = methodName;

        this.retryTime = 0;
    }*/
}
