package com.wpx.model.user.wechatoauser.pojo.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* @author 不会飞的小鹏
* @since 2021-08-14
*/
@Data
    @EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WechatOaUserWebAddDTO implements Serializable {

private static final long serialVersionUID = 1L;

                    
        @ApiModelProperty(value = "用户ID")
                                        @NotNull(message = "用户ID不能为空")
    private Long userId;

        @ApiModelProperty(value = "微信用户ID")
                                        @NotNull(message = "微信用户ID不能为空")
    private Long wechatUserId;

        @ApiModelProperty(value = "应用ID")
                                        @NotNull(message = "应用ID不能为空")
    private Long appId;

        @ApiModelProperty(value = "所属公众号标识")
                                        @NotBlank(message = "所属公众号标识不能为空")
    private String appSign;

        @ApiModelProperty(value = "公众号用户唯一标识")
                                        @NotBlank(message = "公众号用户唯一标识不能为空")
    private String openId;

        @ApiModelProperty(value = "微信用户唯一标识")
                                        @NotBlank(message = "微信用户唯一标识不能为空")
    private String unionIdId;

        @ApiModelProperty(value = "手机号码")
                                        @NotBlank(message = "手机号码不能为空")
    private String phone;

        @ApiModelProperty(value = "用户昵称")
                                        @NotBlank(message = "用户昵称不能为空")
    private String nickname;

        @ApiModelProperty(value = "用户头像")
                                        @NotBlank(message = "用户头像不能为空")
    private String avatar;

        @ApiModelProperty(value = "用户性别")
                                        @NotNull(message = "用户性别不能为空")
    private Integer gender;

        @ApiModelProperty(value = "所在地")
                                        @NotBlank(message = "所在地不能为空")
    private String location;

                    
                    
                    


}