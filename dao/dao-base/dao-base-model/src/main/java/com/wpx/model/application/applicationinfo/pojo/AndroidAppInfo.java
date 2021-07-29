package com.wpx.model.application.applicationinfo.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/27 19:09
 * @Description 安卓应用信息格式
 */
@Data
public class AndroidAppInfo {

    @ApiModelProperty(value = "firebase平台申请的databaseUrl")
    private String databaseUrl;

    @ApiModelProperty(value = "应用在firebase下载的google.json")
    private String googleJson;

    @ApiModelProperty(value = "firebase的包名")
    private String packageName;

}
