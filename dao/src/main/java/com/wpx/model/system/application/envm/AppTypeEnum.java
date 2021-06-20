package com.wpx.model.system.application.envm;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum AppTypeEnum implements IEnum<Integer>, Serializable {

    ANDROID(0, "安卓app"),

    IOS(1, "iosapp"),

    ;

    private static final long serialVersionUID = 1L;

    private Integer value;

    private String name;

    AppTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 枚举数据库存储值
     */
    @Override
    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

}
