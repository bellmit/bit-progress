package com.wpx.model.menu.vo;

import com.wpx.model.WechatResult;
import com.wpx.model.menu.Button;

import java.util.List;

/**
 * @author 不会飞的小鹏
 */
public class ButtonTryMatchVO extends WechatResult {

    /**
     * 菜单列表
     */
    private List<Button> button;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "ButtonTryMatchVO{" +
                "button=" + button +
                '}';
    }

}
