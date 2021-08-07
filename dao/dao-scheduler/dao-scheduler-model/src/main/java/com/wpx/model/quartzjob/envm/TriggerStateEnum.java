package com.wpx.model.quartzjob.envm;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.util.Objects;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 触发器状态
 */
public enum TriggerStateEnum implements IEnum<Integer> {

    NONE(0, "状态"),
    NORMAL(1, ""),
    PAUSED(2, ""),
    COMPLETE(3, ""),
    ERROR(4, ""),
    BLOCKED(5, ""),
    ;

    private Integer value;
    private String name;

    TriggerStateEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return name;
    }

    public static TriggerStateEnum valueOf(Integer value) {
        for (TriggerStateEnum triggerState : TriggerStateEnum.values()) {
            if (Objects.equals(triggerState.getValue(), value)) {
                return triggerState;
            }
        }
        return null;
    }

}
