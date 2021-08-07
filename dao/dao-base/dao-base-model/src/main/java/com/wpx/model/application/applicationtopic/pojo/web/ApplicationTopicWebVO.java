package com.wpx.model.application.applicationtopic.pojo.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * @Author: 不会飞的小鹏 
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApplicationTopicWebVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用主题ID")
    private Long applicationTopicId;

    @ApiModelProperty(value = "应用ID")
    private Long applicationId;

    @ApiModelProperty(value = "主题名称")
    private String topic;

    @ApiModelProperty(value = "主题消息图标")
    private String icon;

    @ApiModelProperty(value = "主题消息颜色")
    private String color;

    @ApiModelProperty(value = "主题消息标题")
    private String title;

    @ApiModelProperty(value = "主题消息内容")
    private String body;

    @ApiModelProperty(value = "是否默认订阅，true：默认订阅，false：默认不订阅")
    private Boolean defaulted;

}
