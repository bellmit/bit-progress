package com.wpx.model.quartzjob.pojo;

import com.wpx.model.quartzjob.envm.TriggerStateEnum;
import com.wpx.model.quartzjob.envm.TriggerType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wpx
 * @Description:
 **/
@Data
public class QuartzJobQueryDTO implements Serializable {

    private static final long serialVersionUID = 6575630849578006711L;

    @ApiModelProperty(value = "任务类型")
    private TriggerType triggerType;

    /**
     * 定时任务分组
     */
    @ApiModelProperty(value = "任务分组")
    private String groupName;

    /**
     * 触发器状态
     */
    @ApiModelProperty(value = "触发器状态")
    private TriggerStateEnum triggerState;

}