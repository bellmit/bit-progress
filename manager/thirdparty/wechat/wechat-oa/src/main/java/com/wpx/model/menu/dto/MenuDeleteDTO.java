package com.wpx.model.menu.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 */
public class MenuDeleteDTO {

    @JSONField(name = "menuid")
    private String menuId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "MenuDeleteDTO{" +
                "menuId='" + menuId + '\'' +
                '}';
    }

}
