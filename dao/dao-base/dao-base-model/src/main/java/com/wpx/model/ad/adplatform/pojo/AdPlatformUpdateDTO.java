package com.wpx.model.ad.adplatform.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 广告平台更新DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AdPlatformUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "广告平台ID")
    @NotNull(message = "广告平台ID不能为空")
    private Long adPlatformId;

    @ApiModelProperty(value = "广告平台名称")
    private String adPlatformName;

    @ApiModelProperty(value = "状态，0：禁用，1：启用")
    @NotNull(message = "状态，0：禁用，1：启用不能为空")
    private Integer flag;


}
