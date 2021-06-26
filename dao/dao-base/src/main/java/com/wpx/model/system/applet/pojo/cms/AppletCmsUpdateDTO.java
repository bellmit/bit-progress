package com.wpx.model.system.applet.pojo.cms;

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
public class AppletCmsUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小程序ID")
    @NotNull(message = "小程序ID不能为空")
    private Long appletId;

    @ApiModelProperty(value = "小程序标识")
    private String appletSign;

    @ApiModelProperty(value = "小程序名称")
    private String appletName;

    @ApiModelProperty(value = "小程序id")
    private String appid;

    @ApiModelProperty(value = "小程序凭证密钥")
    private String secret;

    @ApiModelProperty(value = "小程序商户号")
    private String mchid;


}
