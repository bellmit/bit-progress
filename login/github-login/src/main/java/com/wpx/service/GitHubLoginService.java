package com.wpx.service;

import com.alibaba.fastjson.JSON;
import com.wpx.constant.StringConstants;
import com.wpx.exception.CustomizeException;
import com.wpx.exception.ExceptionMessage;
import com.wpx.constant.GitHubLoginUrl;
import com.wpx.exception.GitHubLoginExceptionMessage;
import com.wpx.model.AuthorizationParams;
import com.wpx.model.GitHubUser;
import com.wpx.okhttp.util.OkHttpClientUtils;
import okhttp3.*;

import java.io.IOException;

/**
 * @author 不会飞的小鹏
 * @Description: GitHub登录服务
 */
public class GitHubLoginService {

    /**
     * 获取AccessToken
     *
     * @param params
     */
    public String getAccessToken(AuthorizationParams params) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        String body = JSON.toJSONString(params);
        try {
            String result = OkHttpClientUtils.doPost(GitHubLoginUrl.GITHUB_ACCESS_TOKEN_URL, body, mediaType);
            return result.split(StringConstants.AND)[0].split(StringConstants.EQUAL_SIGN)[1];
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomizeException(GitHubLoginExceptionMessage.GITHUB_ACCESS_TOKEN_REQUEST_ERROR);
        }
    }

    /**
     * 获取用户信息
     *
     * @param accessToken
     */
    public GitHubUser getGitHubUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(GitHubLoginUrl.GITHUB_USER_URL + "?access_token=" + accessToken)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            return JSON.parseObject(string, GitHubUser.class);
        }catch (IOException e){
            e.printStackTrace();
            throw CustomizeException.error(GitHubLoginExceptionMessage.GITHUB_USER_REQUEST_ERROR);
        }
    }

}
