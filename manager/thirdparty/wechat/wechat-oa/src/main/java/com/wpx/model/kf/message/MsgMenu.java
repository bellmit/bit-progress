package com.wpx.model.kf.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author 不会飞的小鹏
 * @description： 菜单消息
 */
public class MsgMenu implements KfBaseMessage {

    @JSONField(name = "head_content")
    private String headContent;

    /**
     * 菜单列表
     */
    private List<Menu> list;

    /**
     * 点击后提示文本
     */
    @JSONField(name = "tail_content")
    private String tailContent;

    public String getHeadContent() {
        return headContent;
    }

    public void setHeadContent(String headContent) {
        this.headContent = headContent;
    }

    public List<Menu> getList() {
        return list;
    }

    public void setList(List<Menu> list) {
        this.list = list;
    }

    public String getTailContent() {
        return tailContent;
    }

    public void setTailContent(String tailContent) {
        this.tailContent = tailContent;
    }

}
