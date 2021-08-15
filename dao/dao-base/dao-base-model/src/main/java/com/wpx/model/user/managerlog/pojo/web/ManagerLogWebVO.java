package com.wpx.model.user.managerlog.pojo.web;

import com.wpx.model.user.managerlog.envm.MethodEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author 不会飞的小鹏 
 * created on 2021-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ManagerLogWebVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员操作日志ID")
    private Long managerLogId;

    @ApiModelProperty(value = "管理员ID")
    private Long managerId;

    @ApiModelProperty(value = "请求方法类型")
    private MethodEnum method;

    @ApiModelProperty(value = "请求uri")
    private String uri;

    @ApiModelProperty(value = "参数列表")
    private String args;

    @ApiModelProperty(value = "创建时间", dataType = "java.lang.Long")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间", dataType = "java.lang.Long")
    private LocalDateTime updateTime;


}
