package com.wpx.model.quartzjob.pojo;

import com.wpx.model.quartzjob.envm.TriggerType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: wpx
 * @Description:
 **/
@Data
public class QuartzJobAddDTO implements Serializable {

    private static final long serialVersionUID = 6575630849578006711L;

    @ApiModelProperty(value = "任务名称")
    private String jobName;

    @ApiModelProperty(value = "任务类型")
    private TriggerType triggerType;

    /**
     * 重试次数
     */
    @ApiModelProperty(value = "重试次数")
    private Integer retryTime;

    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @ApiModelProperty(value = "延迟时长")
    private Long duration;

    /**
     * bean名
     */
    @ApiModelProperty(value = "任务执行的bean名称")
    private String beanName;

    /**
     * 方法名
     */
    @ApiModelProperty(value = "任务执行的method名称")
    private String methodName;

    @ApiModelProperty(value = "参数列表")
    private String argsList;

    @ApiModelProperty(value = "参数类型列表，仅支持jdk的类")
    private String argsClassList;

    /**
     * 定时任务分组
     */
    @ApiModelProperty(value = "任务分组")
    private String groupName;

    /**
     * 应用名
     */
    private String applicationName;

}
