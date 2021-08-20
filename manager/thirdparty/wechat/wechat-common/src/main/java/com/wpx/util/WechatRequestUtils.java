package com.wpx.util;

import com.wpx.exception.CommonException;
import com.wpx.okhttp.constant.OkHttpConstants;
import com.wpx.okhttp.util.OkHttpClientUtils;
import okhttp3.MediaType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.wpx.constant.WechatConstant.ACCESS_TOKEN;

/**
 * @author 不会飞的小鹏
 * @description： 微信请求工具类
 */
public class WechatRequestUtils {

    /**
     * 统一get请求入口
     *
     * @param url         请求url
     * @param accessToken 微信接口调用凭证
     * @return 请求结果
     * @throws IOException
     */
    public static String doGetWithAccessToken(String url, String accessToken) {
        return doGetWithAccessToken(url, accessToken, null);
    }

    /**
     * 统一get请求入口
     *
     * @param url         请求url
     * @param accessToken 微信接口调用凭证
     * @return 请求结果
     * @throws IOException
     */
    public static String doGetWithAccessToken(String url, String accessToken, Map<String, String> addParams) {
        HashMap<String, String> params = new HashMap<>(8);
        params.put(ACCESS_TOKEN, accessToken);
        if (CollectionUtils.nonEmpty(addParams)) {
            params.putAll(addParams);
        }
        try {
            return OkHttpClientUtils.doGetWithParam(url, params);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(500, e.getMessage(), "GET 微信接口 [" + url + "] 请求异常");
        }
    }

    /**
     * 统一post请求入口
     *
     * @param url         请求url
     * @param accessToken 微信接口调用凭证
     * @param body        请求数据
     * @return 请求结果
     * @throws IOException
     */
    public static String doPostWithAccessToken(String url, String accessToken, String body) {
        MediaType mediaType = OkHttpConstants.MEDIA_TYPE_JSON;
        return doPostWithAccessToken(url, mediaType, accessToken, body, null);
    }

    /**
     * 统一post请求入口
     *
     * @param url         请求url
     * @param mediaType   数据提交方式
     * @param accessToken 微信接口调用凭证
     * @param body        请求数据
     * @param addParams   补充参数
     * @return 请求结果
     * @throws IOException
     */
    public static String doPostWithAccessToken(String url, MediaType mediaType, String accessToken, String body,
                                               Map<String, String> addParams) {
        HashMap<String, String> params = new HashMap<>(8);
        params.put(ACCESS_TOKEN, accessToken);
        if (CollectionUtils.nonEmpty(addParams)) {
            params.putAll(addParams);
        }
        try {
            return OkHttpClientUtils.doPost(url, body, mediaType, params, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(500, e.getMessage(), "POST 微信接口 [" + url + "] 请求异常");
        }
    }

    public static String doPost(String url, String body) {
        try {
            MediaType mediaType = OkHttpConstants.MEDIA_TYPE_JSON;
            return OkHttpClientUtils.doPost(url, body, mediaType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(500, e.getMessage(), "POST 微信接口 [" + url + "] 请求异常");
        }
    }

}
