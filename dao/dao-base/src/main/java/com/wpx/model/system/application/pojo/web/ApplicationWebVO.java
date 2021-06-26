package com.wpx.model.system.application.pojo.web;

import com.wpx.model.system.application.envm.AppTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author wupengxiao
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApplicationWebVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用ID")
    private Long applicationId;

    @ApiModelProperty(value = "应用类型，IOS，ANDROID")
    private AppTypeEnum appType;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "应用标识")
    private String appSign;

    @ApiModelProperty(value = "应用消息图标")
    private String icon;

    @ApiModelProperty(value = "应用消息字体颜色")
    private String color;

    @ApiModelProperty(value = "应用消息标题")
    private String title;

    @ApiModelProperty(value = "应用消息内容")
    private String body;

    @ApiModelProperty(value = "firebase平台申请的databaseUrl")
    private String databaseUrl;

    @ApiModelProperty(value = "应用在firebase下载的google.json")
    private String googleJson;

    @ApiModelProperty(value = "firebase的包名")
    private String packageName;

    @ApiModelProperty(value = "是否禁用")
    private Boolean disabled;

    @ApiModelProperty(value = "创建时间", dataType = "java.lang.Long")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间", dataType = "java.lang.Long")
    private LocalDateTime updateTime;

}
