package com.wpx.model.application.applicationinfo.envm;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

/**
 * @Author: 不会飞的小鹏
 */

public enum AppTypeEnum implements IEnum<Integer>, Serializable {

    /**
     * 安卓应用
     */
    ANDROID(0, "安卓应用"),

    /**
     * IOS应用
     */
    IOS(1, "ios应用"),

    /**
     * 微信小程序
     */
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
