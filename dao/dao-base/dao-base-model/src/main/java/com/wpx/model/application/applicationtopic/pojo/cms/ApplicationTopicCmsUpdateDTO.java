package com.wpx.model.application.applicationtopic.pojo.cms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: 不会飞的小鹏 
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApplicationTopicCmsUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用主题ID")
    @NotNull(message = "应用主题ID不能为空")
    private Long applicationTopicId;

    @ApiModelProperty(value = "应用ID")
    @NotNull(message = "应用ID不能为空")
    private Long applicationId;

    @ApiModelProperty(value = "主题名称")
    @NotNull(message = "主题名称不能为空")
    @NotBlank(message = "主题名称不能为空")
    private String topic;

    @ApiModelProperty(value = "主题消息图标")
    @NotNull(message = "主题消息图标不能为空")
    @NotBlank(message = "主题消息图标不能为空")
    private String icon;

    @ApiModelProperty(value = "主题消息颜色")
    @NotNull(message = "主题消息颜色不能为空")
    @NotBlank(message = "主题消息颜色不能为空")
    private String color;

    @ApiModelProperty(value = "主题消息标题")
    @NotNull(message = "主题消息标题不能为空")
    @NotBlank(message = "主题消息标题不能为空")
    private String title;

    @ApiModelProperty(value = "主题消息内容")
    @NotNull(message = "主题消息内容不能为空")
    @NotBlank(message = "主题消息内容不能为空")
    private String body;

    @ApiModelProperty(value = "是否默认订阅，true：默认订阅，false：默认不订阅")
    @NotNull(message = "是否默认订阅，true：默认订阅，false：默认不订阅不能为空")
    private Boolean defaulted;

}
