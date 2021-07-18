package com.wpx.model.system.applicationinfo.envm;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum AppTypeEnum implements IEnum<Integer>, Serializable {

    ANDROID(0, "安卓应用"),

    IOS(1, "ios应用"),

    WECHAT_APPLET(2, "微信小程序"),

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