package com.wpx.model.system.applicationinfo.pojo.cms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;

import com.wpx.model.system.applicationinfo.envm.AppTypeEnum;
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
@Accessors(chain = true)
public class ApplicationInfoCmsAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @NotNull(message = "应用信息json不能为空")
    @NotBlank(message = "应用信息json不能为空")
    private JSONObject infoJson;

}
