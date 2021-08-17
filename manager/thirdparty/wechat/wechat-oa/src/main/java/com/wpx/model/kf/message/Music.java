package com.wpx.model.kf.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 * @description： 音乐消息
 */
public class Music implements KfBaseMessage {

    /**
     * 图文消息/视频消息/音乐消息/小程序卡片的标题
     */
    private String title;

    /**
     * 图文消息/视频消息/音乐消息的描述
     */
    private String description;

    /**
     * 音乐链接
     */
    @JSONField(name = "musicurl")
    private String musicUrl;

    /**
     * 高品质音乐链接，wifi环境优先使用该链接播放音乐
     */
    @JSONField(name = "hqmusicurl")
    private String hqMusicUrl;

    /**
     * 缩略图/小程序卡片图片的媒体ID
     */
    @JSONField(name = "thumb_media_id")
    private String thumbMediaId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
