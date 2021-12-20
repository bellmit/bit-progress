package com.wpx.model.kf.message;

/**
 * @author 不会飞的小鹏
 *  文本消息
 */
public class Text implements KfBaseMessage {

    /**
     * 文本内容
     * <a href="http://www.qq.com" data-miniprogram-appid="appid" data-miniprogram-path="pages/index/index">点击跳小程序</a>
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
