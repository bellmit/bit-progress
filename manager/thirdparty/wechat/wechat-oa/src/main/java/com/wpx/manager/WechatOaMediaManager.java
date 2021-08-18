package com.wpx.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpResponse;
import com.wpx.constant.WechatOaUrl;
import com.wpx.model.media.MediaTypeEnum;
import com.wpx.model.media.MediaVO;
import com.wpx.okhttp.constant.OkHttpConstants;
import com.wpx.util.WechatRequestUtils;
import com.wpx.util.WechatResultUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.wpx.constant.WechatOaConstants.MEDIA;
import static com.wpx.constant.WechatOaConstants.TYPE;
import static com.wpx.constant.WechatOaUrl.UPLOAD_MEDIA_URL;
import static com.wpx.okhttp.constant.OkHttpConstants.MEDIA_TYPE_FILE;

/**
 * @author 不会飞的小鹏
 * @description： 微信公众号素材管理
 */
public class WechatOaMediaManager {

    /**
     * 新增临时素材
     * 媒体文件在后台保存时间为3天，即3天后media_id失效。
     *
     * @param accessToken
     * @param mediaType
     * @param media
     * @return MediaVO
     */
    public static MediaVO uploadMedia(String accessToken, MediaTypeEnum mediaType, File media) {
        JSONObject mediaBody = new JSONObject();
        mediaBody.put(MEDIA, media);
        Map<String, String> params = new HashMap<>(8);
        params.put(TYPE, mediaType.getName());
        String body = JSON.toJSONString(mediaBody);
        String result = WechatRequestUtils.doPostWithAccessToken(UPLOAD_MEDIA_URL, MEDIA_TYPE_FILE, accessToken, body, params);
        return WechatResultUtils.wechatResultCheck(result, MediaVO.class);
    }

}
