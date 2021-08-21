package com.wpx.manager;

import com.wpx.model.media.MediaVO;
import com.wpx.util.JsonUtils;
import com.wpx.model.media.MediaTypeEnum;
import com.wpx.model.media.MediaUploadVO;
import com.wpx.util.WechatRequestUtils;
import com.wpx.util.WechatResultUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.wpx.constant.WechatOaConstants.*;
import static com.wpx.constant.WechatOaUrl.GET_MEDIA_URL;
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
    public static MediaUploadVO uploadMedia(String accessToken, MediaTypeEnum mediaType, File media) {
        Map<String, Object> mediaBody = new HashMap<>(4);
        mediaBody.put(MEDIA, media);
        Map<String, String> params = new HashMap<>(4);
        params.put(TYPE, mediaType.getName());
        String body = JsonUtils.serializeObject(mediaBody);
        String result = WechatRequestUtils.doPostWithAccessToken(UPLOAD_MEDIA_URL, MEDIA_TYPE_FILE, accessToken, body, params);
        return WechatResultUtils.wechatResultCheck(result, MediaUploadVO.class);
    }

    /**
     * 获取临时素材
     *
     * @param mediaId
     * @return
     */
    public MediaVO getTemporaryMaterial(String accessToken, String mediaId, MediaTypeEnum mediaType) {
        Map<String, String> params = new HashMap<>(4);
        params.put(MEDIA_ID, mediaId);
        String result = WechatRequestUtils.doGetWithAccessToken(GET_MEDIA_URL, accessToken, params);
        return WechatResultUtils.wechatResultCheck(result, MediaVO.class);
    }

}
