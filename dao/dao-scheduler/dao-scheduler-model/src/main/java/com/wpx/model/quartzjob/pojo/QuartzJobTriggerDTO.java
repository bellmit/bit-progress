package com.wpx.model.quartzjob.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wpx
 * @Description:
 **/
@Data
public class QuartzJobTriggerDTO implements Serializable {

    private static final long serialVersionUID = 6575630849578006745L;

    private String triggerKeyName;

    private String triggerKeyGroup;

}
