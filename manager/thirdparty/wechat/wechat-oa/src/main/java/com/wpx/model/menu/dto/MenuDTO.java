package com.wpx.model.menu.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.wpx.model.menu.Button;
import com.wpx.model.menu.MatchRule;

import java.util.List;

/**
 * @author 不会飞的小鹏
 */
public class MenuDTO {

    /**
     * 菜单列表
     */
    private List<Button> button;

    /**
     * 菜单匹配规则
     */
    @JSONField(name = "matchrule")
    private MatchRule matchRule;

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

    @Override
    public String toString() {
        return "MenuDTO{" +
                "button=" + button +
                ", matchRule=" + matchRule +
                '}';
    }

}
