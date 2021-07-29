package com.wpx.model.application.application.pojo.cms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wupengxiao
 * @since 2021-06-07
 */
@Data
public class ApplicationCmsDisabledDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用ID")
    @NotNull(message = "应用ID不能为空")
    private Long applicationId;

}
