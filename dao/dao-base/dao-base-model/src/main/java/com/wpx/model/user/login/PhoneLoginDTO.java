package com.wpx.model.user.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 不会飞的小鹏
 */
@Data
public class PhoneLoginDTO {

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    @Size(max = 11, min = 11, message = "手机号码只能11位数")
    private String phone;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "短信验证码")
    @NotNull(message = "短信验证码不能为空")
    @NotBlank(message = "短信验证码不能为空")
    private String smsCaptcha;

}
