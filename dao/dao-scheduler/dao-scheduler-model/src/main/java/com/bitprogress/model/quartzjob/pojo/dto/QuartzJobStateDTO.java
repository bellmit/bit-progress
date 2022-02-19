package com.bitprogress.model.quartzjob.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 不会飞的小鹏
 */
@Data
public class QuartzJobStateDTO {

    @ApiModelProperty(value = "定时任务ID")
    @NotNull(message = "定时任务ID不能为空")
    private Long quartzJobId;

}
