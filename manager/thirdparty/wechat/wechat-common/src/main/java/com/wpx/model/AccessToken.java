package com.wpx.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 * @description： 微信接口调用凭证
 */
public class AccessToken {

    /**
     * 微信接口调用凭证 access_token
     */
    @JSONField(name = "access_token")
    private String accessToken;

    /**
     * 微信接口调用凭证有效时间，单位秒
     */
    @JSONField(name = "expires_in")
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

}
