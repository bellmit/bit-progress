package com.wpx.model.system.adplatform;

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
 * @since 2021-06-04
 */
@ApiModel(value = "")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_ad_platform")
public class AdPlatform implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ad_platform_id", type = IdType.AUTO)
    private Long adPlatformId;

    @ApiModelProperty(value = "广告平台名称")
    private String adPlatformName;

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
