package com.wpx.model.user.user.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @author wupengxiao
 * @since 2021-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    @ApiModelProperty(value = "fcm token")
    private String fcmToken;

    @ApiModelProperty(value = "0：禁用，1：启用")
    private Integer flag;


}
