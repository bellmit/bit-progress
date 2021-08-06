package com.wpx.model.application.applicationinfo.pojo.cms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import com.wpx.model.application.applicationinfo.envm.AppTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * @author wupengxiao
 * @since 2021-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApplicationInfoCmsUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用配置ID")
    @NotNull(message = "应用配置ID不能为空")
    private Long applicationInfoId;

    @ApiModelProperty(value = "应用ID")
    @NotNull(message = "应用ID不能为空")
    private Long applicationId;

    @ApiModelProperty(value = "应用名称")
    @NotNull(message = "应用名称不能为空")
    @NotBlank(message = "应用名称不能为空")
    private String appName;

    @ApiModelProperty(value = "应用类型，IOS，ANDROID，WECHAT_APPLET")
    @NotNull(message = "应用类型，IOS，ANDROID，WECHAT_APPLET不能为空")
    private AppTypeEnum appType;

    @ApiModelProperty(value = "应用标识")
    @NotNull(message = "应用标识不能为空")
    @NotBlank(message = "应用标识不能为空")
    private String appSign;

    @ApiModelProperty(value = "应用信息json")
    private JSONObject infoJson;

}
