package com.wpx.model.quartzjob.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 不会飞的小鹏
 *  批量添加定时任务
 */
@Data
public class QuartzJobListAddDTO implements Serializable {

    private static final long serialVersionUID = 6575630849578006312L;

    @ApiModelProperty(value = "定时任务添加列表")
    private List<QuartzJobAddDTO> quartzAddList;

}
