package com.wpx.model.media;

import com.alibaba.fastjson.annotation.JSONField;
import com.wpx.model.WechatResult;

/**
 * @author 不会飞的小鹏
 */
public class MediaVO extends WechatResult {

    private String type;

    @JSONField(name = "media_id")
    private String mediaId;

    @JSONField(name = "created_at")
    private Integer createdAt;

    private String url;

}
