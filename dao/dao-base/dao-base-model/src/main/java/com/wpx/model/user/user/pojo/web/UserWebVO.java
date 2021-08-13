package com.wpx.model.user.user.pojo.web;

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
import com.luwei.common.config.ToTimeStampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
* @author 不会飞的小鹏
* created on 2021-08-13
*/
@Data
    @EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserWebVO implements Serializable {

private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "用户ID")
                    private Long userId;

        @ApiModelProperty(value = "用户所属应用类型")
                    private Integer appType;

        @ApiModelProperty(value = "用户所属应用标识")
                    private String appSign;

        @ApiModelProperty(value = "是否禁用，0：false，不禁言；1：true，禁用")
                    private Boolean flag;

        @ApiModelProperty(value = "创建时间", dataType = "java.lang.Long")
                    @JsonSerialize(using = ToTimeStampSerializer.class)
    private LocalDateTime createTime;

        @ApiModelProperty(value = "更新时间", dataType = "java.lang.Long")
                    @JsonSerialize(using = ToTimeStampSerializer.class)
    private LocalDateTime updateTime;

                


}
