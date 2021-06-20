package com.wpx.model.system.application.pojo.cms;

import com.wpx.model.system.application.envm.AppTypeEnum;
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
    private Integer applicationId;

    @ApiModelProperty(value = "应用类型，IOS，ANDROID")
    @NotNull(message = "应用类型，IOS，ANDROID不能为空")
    private AppTypeEnum appType;

    @ApiModelProperty(value = "应用名称")
    @NotNull(message = "应用名称不能为空")
    @NotBlank(message = "应用名称不能为空")
    private String appName;

    @ApiModelProperty(value = "应用标识")
    @NotNull(message = "应用标识不能为空")
    @NotBlank(message = "应用标识不能为空")
    private String appSign;

    @ApiModelProperty(value = "应用图标")
    @NotNull(message = "应用图标不能为空")
    @NotBlank(message = "应用图标不能为空")
    private String icon;

    @ApiModelProperty(value = "应用消息字体颜色")
    @NotNull(message = "应用消息字体颜色不能为空")
    @NotBlank(message = "应用消息字体颜色不能为空")
    private String color;

    @ApiModelProperty(value = "应用消息标题")
    @NotNull(message = "应用消息标题不能为空")
    @NotBlank(message = "应用消息标题不能为空")
    private String title;

    @ApiModelProperty(value = "应用消息内容")
    @NotNull(message = "应用消息内容不能为空")
    @NotBlank(message = "应用消息内容不能为空")
    private String body;

    @ApiModelProperty(value = "firebase平台申请的databaseUrl")
    @NotNull(message = "firebase平台申请的databaseUrl不能为空")
    @NotBlank(message = "firebase平台申请的databaseUrl不能为空")
    private String databaseUrl;

    @ApiModelProperty(value = "应用在firebase下载的google.json")
    @NotNull(message = "应用在firebase下载的google.json不能为空")
    @NotBlank(message = "应用在firebase下载的google.json不能为空")
    private String googleJson;

    @ApiModelProperty(value = "firebase的包名")
    @NotNull(message = "firebase的包名不能为空")
    @NotBlank(message = "firebase的包名不能为空")
    private String packageName;

}
