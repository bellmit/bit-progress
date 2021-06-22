package com.wpx.model.system.applicationtopic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "应用信息")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_application_topic")
public class ApplicationTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用主题ID")
    @TableId(value = "application_topic_id", type = IdType.AUTO)
    private Integer applicationTopicId;

    @ApiModelProperty(value = "应用ID")
    private Integer applicationId;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：true：已删除，false：未删除")
    @TableLogic
    private Boolean deleted;

}
