package com.wpx.model.menu;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author huangchangrong
 * @desc 菜单按钮
 * @data 2021/4/8
 */
public class Button {
    /**
     * 菜单类型
     */
    private ButtonTypeEnum type;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;

    /**
     * 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     */
    private String url;

    /**
     *  媒体id
     */
    @JSONField(name = "media_id")
    private String mediaId;

    /**
     * 小程序的appid
     */
    @JSONField(name = "appid")
    private String appId;

    /**
     * 小程序的页面路径
     */
    @JSONField(name = "pagepath")
    private String pagePath;

    /**
     * 子菜单
     */
    @JSONField(name = "sub_button")
    private List<Button> subButton;

}
