package com.wpx.model.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wpx.model.WechatResult;

/**
 * @author 不会飞的小鹏
 */
public class MediaVO extends WechatResult {

    private String type;

    @JsonProperty("media_id")
    private String mediaId;

    @JsonProperty("created_at")
    private Integer createdAt;

    private String url;

}
