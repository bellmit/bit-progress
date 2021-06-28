package com.wpx.model.system.applicationinfo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value = "应用配置信息")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_application_info")
public class ApplicationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用配置ID")
    @TableId(value = "application_info_id", type = IdType.AUTO)
    private Long applicationInfoId;

    @ApiModelProperty(value = "应用ID")
    private Long applicationId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "应用类型，IOS，ANDROID，WECHAT_APPLET")
    private AppTypeEnum appType;

    @ApiModelProperty(value = "应用标识")
    private String appSign;

    @ApiModelProperty(value = "应用信息json")
    private String infoJson;

    @ApiModelProperty(value = "是否禁用")
    private Boolean disabled;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：true：已删除，false：未删除")
    @TableLogic
    private Boolean deleted;

}
