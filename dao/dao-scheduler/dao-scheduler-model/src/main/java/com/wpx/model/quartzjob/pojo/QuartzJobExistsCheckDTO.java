package com.wpx.model.quartzjob.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 不会飞的小鹏
 * @Description: 检查任务是否存在
 **/
@Data
public class QuartzJobExistsCheckDTO implements Serializable {

    private static final long serialVersionUID = 6575630849578006711L;

    @ApiModelProperty(value = "jobName", required = true)
    private String jobName;

    @ApiModelProperty(value = "job分组名称")
    private String jobGroupName;

}