package com.wpx.model.system.applet.pojo.cms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author wupengxiao
 * @since 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppletCmsAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小程序标识")
    @NotNull(message = "小程序标识不能为空")
    @NotBlank(message = "小程序标识不能为空")
    private String appletSign;

    @ApiModelProperty(value = "小程序类型，WE_CHAT，ALI")
    @NotNull(message = "小程序类型，WE_CHAT，ALI不能为空")
    private Integer appletType;

    @ApiModelProperty(value = "小程序名称")
    @NotNull(message = "小程序名称不能为空")
    @NotBlank(message = "小程序名称不能为空")
    private String appletName;

    @ApiModelProperty(value = "小程序id")
    @NotNull(message = "小程序id不能为空")
    @NotBlank(message = "小程序id不能为空")
    private String appid;

    @ApiModelProperty(value = "小程序凭证密钥")
    @NotNull(message = "小程序凭证密钥不能为空")
    @NotBlank(message = "小程序凭证密钥不能为空")
    private String secret;

    @ApiModelProperty(value = "小程序商户号")
    @NotNull(message = "小程序商户号不能为空")
    @NotBlank(message = "小程序商户号不能为空")
    private String mchid;

}
