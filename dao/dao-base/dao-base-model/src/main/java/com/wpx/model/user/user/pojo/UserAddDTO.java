package com.wpx.model.user.user.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wupengxiao
 * @since 2021-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "fcm token")
    @NotNull(message = "fcm token不能为空")
    @NotBlank(message = "fcm token不能为空")
    private String fcmToken;

    @ApiModelProperty(value = "app_sign")
    @NotNull(message = "app_sign不能为空")
    @NotBlank(message = "app_sign不能为空")
    private String appSign;

}
