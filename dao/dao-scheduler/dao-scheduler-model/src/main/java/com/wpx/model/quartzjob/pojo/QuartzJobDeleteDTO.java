package com.wpx.model.quartzjob.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 任务删除
 */
@Data
public class QuartzJobDeleteDTO implements Serializable {

    private static final long serialVersionUID = 6575630849578006711L;

    private String jobKeyName;

    private String jobKeyGroup;

    private String triggerKeyName;

    private String triggerKeyGroup;

}
