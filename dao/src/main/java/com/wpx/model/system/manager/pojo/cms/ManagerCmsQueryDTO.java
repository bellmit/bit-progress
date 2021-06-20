package com.wpx.model.system.manager.pojo.cms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wupengxiao
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ManagerCmsQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

}
