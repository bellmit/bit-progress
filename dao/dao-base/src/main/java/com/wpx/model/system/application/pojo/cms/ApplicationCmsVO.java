package com.wpx.model.system.application.pojo.cms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wupengxiao
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApplicationCmsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用ID")
    private Long applicationId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "应用标识")
    private String appSign;

    @ApiModelProperty(value = "是否禁用")
    private Boolean disabled;

}
