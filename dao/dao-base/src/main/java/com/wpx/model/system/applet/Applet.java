package com.wpx.model.system.applet;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author wupengxiao
 * @since 2021-06-26
 */
@ApiModel(value = "小程序信息")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_applet")
public class Applet implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小程序ID")
    @TableId(value = "applet_id", type = IdType.AUTO)
    private Long appletId;

    @ApiModelProperty(value = "小程序标识")
    private String appletSign;

    @ApiModelProperty(value = "小程序类型，WE_CHAT，ALI")
    private Integer appletType;

    @ApiModelProperty(value = "小程序名称")
    private String appletName;

    @ApiModelProperty(value = "小程序id")
    private String appid;

    @ApiModelProperty(value = "小程序凭证密钥")
    private String secret;

    @ApiModelProperty(value = "小程序商户号")
    private String mchid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除：true：已删除，false：未删除")
    @TableLogic
    private Boolean deleted;


}
