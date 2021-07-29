package com.wpx.model.application.application.pojo.cms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wupengxiao
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApplicationCmsUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用ID")
    @NotNull(message = "应用ID不能为空")
    private Long applicationId;

    @ApiModelProperty(value = "应用名称")
    @NotNull(message = "应用名称不能为空")
    @NotBlank(message = "应用名称不能为空")
    private String appName;

    @ApiModelProperty(value = "应用标识")
    @NotNull(message = "应用标识不能为空")
    @NotBlank(message = "应用标识不能为空")
    private String appSign;

}
