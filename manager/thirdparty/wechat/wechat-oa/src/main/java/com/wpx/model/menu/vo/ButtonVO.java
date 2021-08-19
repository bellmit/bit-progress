package com.wpx.model.menu.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.wpx.model.menu.Button;
import com.wpx.model.menu.MatchRule;

import java.util.List;

/**
 * @author 不会飞的小鹏
 */
public class ButtonVO {

    /**
     * 菜单列表
     */
    private List<Button> button;

    /**
     * 匹配规则
     */
    @JSONField(name = "matchrule")
    private MatchRule matchRule;

    /**
     * 菜单ID
     */
    @JSONField(name = "menuid")
    private String menuId;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    public MatchRule getMatchRule() {
        return matchRule;
    }

    public void setMatchRule(MatchRule matchRule) {
        this.matchRule = matchRule;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "ButtonVO{" +
                "button=" + button +
                ", matchRule=" + matchRule +
                ", menuId='" + menuId + '\'' +
                '}';
    }

}
