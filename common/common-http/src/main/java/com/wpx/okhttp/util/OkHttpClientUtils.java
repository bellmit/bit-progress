package com.wpx.okhttp.util;

import okhttp3.*;

import java.io.IOException;

/**
 * @author 不会飞的小鹏
 * create on 2021/7/19 20:59
 * @Description: okhttp请求工具类
 */
public class OkHttpClientUtils {

    /**
     * 发起POST请求
     *
     * @param url
     * @param body
     * @param mediaType
     */
    public static String doPost(String url, String body, MediaType mediaType) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(mediaType, body);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return client.newCall(request).execute().body().string();
    }

    /**
     * 发起GET请求
     *
     * @param url
     */
    public static String doGet(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request).execute().body().string();
    }

}
