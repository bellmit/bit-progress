package com.wpx.manager;

import com.wpx.model.media.*;
import com.wpx.util.JsonUtils;
import com.wpx.util.WechatRequestUtils;
import com.wpx.util.WechatResultUtils;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wpx.constant.WechatOaConstants.*;
import static com.wpx.constant.WechatOaUrl.*;
import static com.wpx.okhttp.constant.OkHttpConstants.*;

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
     * @return MediaVO
     */
    public MediaVO getMedia(String accessToken, String mediaId, MediaTypeEnum mediaType) throws IOException {
        Map<String, String> params = new HashMap<>(4);
        params.put(MEDIA_ID, mediaId);
        if (MediaTypeEnum.IMAGE == mediaType) {
            String result = WechatRequestUtils.doGetWithAccessToken(GET_MEDIA_URL, accessToken, params);
            return WechatResultUtils.wechatResultCheck(result, MediaVO.class);
        }
        File directory = Files.createTempDirectory("media").toFile();
        File file = WechatRequestUtils.doGetWithAccessToken(GET_MEDIA_URL, accessToken, params, directory);
        MediaVO mediaVO = new MediaVO();
        mediaVO.setMedia(file);
        return mediaVO;
    }

    /**
     * 添加永久图文消息素材
     *
     * @param accessToken
     * @param articles
     * @return MediaVO
     */
    public MediaUploadVO addNewsMediaUrl(String accessToken, List<ArticleDTO> articles) {
        Map<String, String> params = new HashMap<>(4);
        params.put(ARTICLES, JsonUtils.serializeObject(articles));
        String body = JsonUtils.serializeObject(params);
        String result = WechatRequestUtils.doPostWithAccessToken(ADD_NEWS_MEDIA_URL, accessToken, body);
        return WechatResultUtils.wechatResultCheck(result, MediaUploadVO.class);
    }

    /**
     * 添加永久图文消息里的图片
     *
     * @param accessToken
     * @param media
     * @return MediaVO
     */
    public MediaUploadVO uploadImgMedia(String accessToken, File media) {
        Map<String, Object> mediaBody = new HashMap<>(4);
        mediaBody.put(MEDIA, media);
        String body = JsonUtils.serializeObject(mediaBody);
        String result = WechatRequestUtils.doPostWithAccessToken(UPLOAD_IMG_MEDIA_URL, MEDIA_TYPE_FILE, accessToken, body);
        return WechatResultUtils.wechatResultCheck(result, MediaUploadVO.class);
    }

    /**
     * 添加其他永久素材
     *
     * @param accessToken
     * @param mediaUploadDTO
     * @return MediaVO
     */
    public MediaUploadVO uploadOtherMedia(String accessToken, MediaUploadDTO mediaUploadDTO) {
        MediaTypeEnum mediaType = mediaUploadDTO.getMediaType();
        Map<String, String> params = new HashMap<>(4);
        params.put(TYPE, mediaType.getName());
        File media = mediaUploadDTO.getMedia();
        String body = JsonUtils.serializeObject(mediaUploadDTO);
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MEDIA_TYPE_FORM_DATA)
                .addFormDataPart("media", media.getName(), RequestBody.create(MEDIA_TYPE_OCTET_STREAM, media))
                .addFormDataPart("description", body)
                .build();
        String result = WechatRequestUtils.doPostWithAccessToken(UPLOAD_OTHER_MEDIA_URL, accessToken, multipartBody, params);
        return WechatResultUtils.wechatResultCheck(result, MediaUploadVO.class);
    }

}
