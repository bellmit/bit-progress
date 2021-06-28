package com.wpx.model.system.applicationinfo.pojo.cms;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;

import com.wpx.model.system.applicationinfo.envm.AppTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author wupengxiao
 * @since 2021-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationInfoCmsQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用配置ID")
    private Long applicationInfoId;

    @ApiModelProperty(value = "应用ID")
    private Long applicationId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "应用类型，IOS，ANDROID，WECHAT_APPLET")
    private AppTypeEnum appType;

    @ApiModelProperty(value = "应用标识")
    private String appSign;

    @ApiModelProperty(value = "是否禁用")
    private Boolean disabled;

}
