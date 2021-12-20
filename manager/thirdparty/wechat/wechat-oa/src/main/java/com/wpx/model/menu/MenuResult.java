package com.wpx.model.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wpx.model.WechatResult;

/**
 * @author 不会飞的小鹏
 *  菜单创建结果
 */
public class MenuResult extends WechatResult {

    @JsonProperty("menuid")
    private String menuId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "MenuResult{" +
                "menuId='" + menuId + '\'' +
                '}';
    }

}
