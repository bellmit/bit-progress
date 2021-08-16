package com.wpx.okhttp.util;

import com.wpx.util.CollectionUtils;
import com.wpx.util.UrlJoinUtils;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author 不会飞的小鹏
 * create on 2021/7/19 20:59
 * @Description: okhttp请求工具类
 */
public class OkHttpClientUtils {

    /**
     * 发起POST请求
     *
     * @param url  请求url
     * @param body  请求内容
     * @param mediaType
     */
    public static String doPost(String url, String body, MediaType mediaType) throws IOException {
        return doPost(url, body, mediaType, null, null);
    }

    /**
     * 发起POST请求
     *
     * @param url  请求url
     * @param body  请求内容
     * @param mediaType
     */
    public static String doPost(String url, String body, MediaType mediaType, Map<String, String> params,
                                Map<String, String> headers) throws IOException {
        if (Objects.isNull(mediaType)) {
            mediaType = MediaType.get("application/json; charset=utf-8");
        }
        if (CollectionUtils.nonEmpty(params)) {
            url = UrlJoinUtils.urlJoinParam(url, params);
        }
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(mediaType, body);
        Request.Builder builder = new Request.Builder().url(url).post(requestBody);
        if (CollectionUtils.nonEmpty(headers)) {
            builder.headers(Headers.of(headers));
        }
        Request request = builder.build();
        return client.newCall(request).execute().body().string();
    }

    /**
     * 发起GET请求
     *
     * @param url  请求url
     * @param headers  请求头
     */
    public static String doGetWithHeader(String url, Map<String, String> headers) throws IOException {
        return doGet(url, null, headers);
    }

    /**
     * 发起GET请求
     *
     * @param url  请求url
     * @param params  请求参数
     */
    public static String doGetWithParam(String url, Map<String, String> params) throws IOException {
        return doGet(url, params, null);
    }

    /**
     * 发起GET请求
     *
     * @param url  请求url
     */
    public static String doGet(String url) throws IOException {
        return doGet(url, null, null);
    }

    /**
     * 发起GET请求
     *
     * @param url  请求url
     * @param params  请求参数
     * @param headers  请求头
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        OkHttpClient client = new OkHttpClient();
        url = UrlJoinUtils.urlJoinParam(url, params);
        Request.Builder builder = new Request.Builder()
                .get()
                .url(url);
        if (CollectionUtils.nonEmpty(headers)) {
            builder.headers(Headers.of(headers));
        }
        Request request = builder.build();
        return client.newCall(request).execute().body().string();
    }

}
