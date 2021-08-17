package com.wpx.model.kf.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 * @description： 素材消息  图片消息/语音消息/图文消息（点击跳转到图文消息页面）
 */
public class Media implements KfBaseMessage {

    /**
     * 媒体ID
     */
    @JSONField(name = "media_id")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
