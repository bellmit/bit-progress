package com.wpx.quartz;

import com.wpx.model.quartzjob.pojo.envm.TriggerStateEnum;
import lombok.Data;

/**
 * @author wpx
 */
@Data
public class TriggerStateItem {

    private String name;

    private TriggerStateEnum triggerState;

}
