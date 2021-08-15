package com.wpx.model.user.wechatoauser.pojo.web;

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
* @since 2021-08-14
*/
@Data
    @EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WechatOaUserWebVO implements Serializable {

private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "微信公众号用户ID")
                    private Long wechatOaUserId;

        @ApiModelProperty(value = "用户ID")
                    private Long userId;

        @ApiModelProperty(value = "微信用户ID")
                    private Long wechatUserId;

        @ApiModelProperty(value = "应用ID")
                    private Long appId;

        @ApiModelProperty(value = "所属公众号标识")
                    private String appSign;

        @ApiModelProperty(value = "公众号用户唯一标识")
                    private String openId;

        @ApiModelProperty(value = "微信用户唯一标识")
                    private String unionIdId;

        @ApiModelProperty(value = "手机号码")
                    private String phone;

        @ApiModelProperty(value = "用户昵称")
                    private String nickname;

        @ApiModelProperty(value = "用户头像")
                    private String avatar;

        @ApiModelProperty(value = "用户性别")
                    private Integer gender;

        @ApiModelProperty(value = "所在地")
                    private String location;

        @ApiModelProperty(value = "创建时间", dataType = "java.lang.Long")
                    @JsonSerialize(using = ToTimeStampSerializer.class)
    private LocalDateTime createTime;

        @ApiModelProperty(value = "更新时间", dataType = "java.lang.Long")
                    @JsonSerialize(using = ToTimeStampSerializer.class)
    private LocalDateTime updateTime;

                


}
