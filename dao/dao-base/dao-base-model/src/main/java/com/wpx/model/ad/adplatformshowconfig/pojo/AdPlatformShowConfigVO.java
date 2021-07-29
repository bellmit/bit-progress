package com.wpx.model.ad.adplatformshowconfig.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author wupengxiao
 * @since 2021-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdPlatformShowConfigVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer adPlatformShowConfigId;

    @ApiModelProperty(value = "广告平台id")
    private Integer adPlatformId;

    @ApiModelProperty(value = "该位置该广告的百分比")
    private Integer percent;

    @ApiModelProperty(value = "展示位置")
    private Integer position;

    @ApiModelProperty(value = "状态，0：禁用，1：启用")
    private Integer flag;

}
