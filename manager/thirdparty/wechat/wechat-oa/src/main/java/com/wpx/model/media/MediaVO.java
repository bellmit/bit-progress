package com.wpx.model.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wpx.model.WechatResult;

import java.io.File;

/**
 * @author 不会飞的小鹏
 */
public class MediaVO extends WechatResult {

    private File media;

    @JsonProperty("video_url")
    private String videoUrl;

    public File getMedia() {
        return media;
    }

    public void setMedia(File media) {
        this.media = media;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "MediaVO{" +
                "media=" + media +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }

}
