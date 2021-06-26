package com.wpx.model.system.adplatformshowconfig.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wupengxiao
 * @since 2021-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AdPlatformShowConfigQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer adPlatformShowConfigId;

    @ApiModelProperty(value = "广告平台id")
    private Long adPlatformId;

    @ApiModelProperty(value = "该位置该广告的百分比")
    private Integer percent;

    @ApiModelProperty(value = "展示位置")
    private Integer position;

    @ApiModelProperty(value = "状态，0：禁用，1：启用")
    private Integer flag;

}
