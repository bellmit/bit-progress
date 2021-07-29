package com.wpx.model.ad.adplatformshowconfig.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wupengxiao
 * @since 2021-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdPlatformShowConfigUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "广告配置ID不能为空")
    private Long adPlatformShowConfigId;

    @ApiModelProperty(value = "广告平台id")
    @NotNull(message = "广告平台id不能为空")
    private Integer adPlatformId;

    @ApiModelProperty(value = "该位置该广告的百分比")
    @NotNull(message = "该位置该广告的百分比不能为空")
    private Integer percent;

    @ApiModelProperty(value = "展示位置")
    @NotNull(message = "展示位置不能为空")
    private Integer position;

    @ApiModelProperty(value = "状态，0：禁用，1：启用")
    @NotNull(message = "状态，0：禁用，1：启用不能为空")
    private Integer flag;

}
