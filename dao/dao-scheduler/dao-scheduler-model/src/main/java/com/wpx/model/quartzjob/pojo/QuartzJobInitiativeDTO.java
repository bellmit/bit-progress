package com.wpx.model.quartzjob.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 不会飞的小鹏
 * @Description: 手动触发任务
 **/
@Data
public class QuartzJobInitiativeDTO implements Serializable {

    private static final long serialVersionUID = 6575630849578006711L;

    /**
     * bean名
     */
    private String beanName;

    /**
     * 方法名
     */
    private String methodName;

}
