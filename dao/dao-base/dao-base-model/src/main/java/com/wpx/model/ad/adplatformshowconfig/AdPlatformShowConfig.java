package com.wpx.model.ad.adplatformshowconfig;

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
 * @Author: 不会飞的小鹏 
 * @since 2021-06-04
 */
@ApiModel(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_ad_platform_show_config")
public class AdPlatformShowConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "广告配置ID")
    @TableId(value = "ad_platform_show_config_id", type = IdType.AUTO)
    private Integer adPlatformShowConfigId;

    @ApiModelProperty(value = "广告平台id")
    private Integer adPlatformId;

    @ApiModelProperty(value = "该位置该广告的百分比")
    private Integer percent;

    @ApiModelProperty(value = "展示位置")
    private Integer position;

    @ApiModelProperty(value = "状态，0：禁用，1：启用")
    private Integer flag;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除状态true:已删除,false:未删除")
    @TableLogic
    private Boolean deleted;

}
