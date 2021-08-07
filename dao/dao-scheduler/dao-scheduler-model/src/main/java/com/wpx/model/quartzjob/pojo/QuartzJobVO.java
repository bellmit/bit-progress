package com.wpx.model.quartzjob.pojo;

import com.wpx.model.quartzjob.envm.TriggerStateEnum;
import com.wpx.model.quartzjob.envm.TriggerType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 任务详情展示类
 */
@Data
public class QuartzJobVO implements Serializable {

    private static final long serialVersionUID = 6575630849578006711L;

    @ApiModelProperty(value = "任务名称")
    private String jobName;

    @ApiModelProperty(value = "触发类型")
    private TriggerType triggerType;

    @ApiModelProperty(value = "重试次数")
    private Integer retryTime;

    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    /**
     * bean名
     */
    @ApiModelProperty(value = "任务触发调用类名")
    private String beanName;

    /**
     * 方法名
     */
    @ApiModelProperty(value = "任务触发调用方法名称")
    private String methodName;

    @ApiModelProperty(value = "任务参数列表")
    private Object[] argsList;

    @ApiModelProperty(value = "任务参数类型列表")
    private Class[] argsClassList;

    /**
     * 定时任务分组
     */
    @ApiModelProperty(value = "定时任务组名称")
    private String groupName;

    /**
     * 服务调用地址
     */
    @ApiModelProperty(value = "任务触发服务调用地址")
    private String serverUrl;

    @ApiModelProperty(value = "任务优先级")
    private Integer priority;

    @ApiModelProperty(value = "触发器执行类型")
    private Integer misfireInstruction;

    @ApiModelProperty(value = "上次触发时间", dataType = "java.lang.Long")
    private LocalDateTime previousFireTime;

    @ApiModelProperty(value = "下次触发时间", dataType = "java.lang.Long")
    private LocalDateTime nextFireTime;

    @ApiModelProperty(value = "任务的key名称")
    private String jobKeyName;

    @ApiModelProperty(value = "任务的key分组")
    private String jobKeyGroup;

    @ApiModelProperty(value = "触发器key名称")
    private String triggerKeyName;

    @ApiModelProperty(value = "触发器的key分组")
    private String triggerKeyGroup;

    @ApiModelProperty(value = "触发器状态，NONE：触发器不存在，NORMAL：正常，PAUSED：暂停，COMPLETE：完成，ERROR：错误,BLOCKED：阻塞")
    private TriggerStateEnum triggerState;

}
