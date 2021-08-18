package com.wpx.model.menu.vo;

import com.wpx.model.WechatResult;

import java.util.List;

/**
 * @author 不会飞的小鹏
 */
public class MenuVO extends WechatResult {

    /**
     * 显示菜单
     */
    private ButtonVO menu;

    /**
     * 一级菜单数组（1-3个）
     */
    private List<ButtonVO> conditionalmenu;

}
