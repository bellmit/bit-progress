package com.wpx.model.quartzjob.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 不会飞的小鹏
 * @Description: 查询单个定时任务
 **/
@Data
public class QuartzJobGetDTO implements Serializable {

    private static final long serialVersionUID = 6575630849578006745L;

    private String jobKeyName;

    private String jobKeyGroup;

}
