package com.wpx.model.system.application.pojo.web;

import com.wpx.model.system.application.envm.AppTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wupengxiao
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationWebQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用ID")
    private Long applicationId;

    @ApiModelProperty(value = "应用类型，IOS，ANDROID")
    private AppTypeEnum appType;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "应用标识")
    private String appSign;

    @ApiModelProperty(value = "firebase平台申请的databaseUrl")
    private String databaseUrl;

    @ApiModelProperty(value = "应用在firebase下载的google.json")
    private String googleJson;

    @ApiModelProperty(value = "firebase的包名")
    private String packageName;

    @ApiModelProperty(value = "是否禁用")
    private Boolean disabled;

}
