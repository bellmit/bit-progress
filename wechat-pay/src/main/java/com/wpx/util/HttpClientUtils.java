package com.wpx.util;

import okhttp3.*;

import java.io.IOException;

/**
 * @author 不会飞的小鹏
 * create on 2021/7/19 20:59
 * @Description: http请求工具类
 */
public class HttpClientUtils {

    /**
     * 发起post请求
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

}
