package com.wpx.model.ad.adplatform.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 广告平台查询DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdPlatformQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "广告平台ID")
    private Long adPlatformId;

    @ApiModelProperty(value = "广告平台名称")
    private String adPlatformName;

    @ApiModelProperty(value = "状态，0：禁用，1：启用")
    private Integer flag;


}
