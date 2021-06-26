package com.wpx.model.system.applet.pojo.cms;

import java.time.LocalDateTime;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wupengxiao
 * @since 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppletCmsQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小程序ID")
    private Long appletId;

    @ApiModelProperty(value = "小程序标识")
    private String appletSign;

    @ApiModelProperty(value = "小程序类型，WE_CHAT，ALI")
    private Integer appletType;

    @ApiModelProperty(value = "小程序名称")
    private String appletName;

    @ApiModelProperty(value = "小程序id")
    private String appid;

    @ApiModelProperty(value = "小程序凭证密钥")
    private String secret;

    @ApiModelProperty(value = "小程序商户号")
    private String mchid;

}
