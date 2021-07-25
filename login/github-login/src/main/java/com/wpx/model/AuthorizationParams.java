package com.wpx.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 */
public  class AuthorizationParams {

    /**
     * GitHub申请的client_id
     */
    @JSONField(name = "client_id")
    private String clientId;

    /**
     * 重定向获取的code
     */
    private String code;

    /**
     * redirect_uri
     */
    @JSONField(name = "redirect_uri")
    private String redirectUri;

    /**
     * client_secret
     */
    @JSONField(name = "client_secret")
    private String clientSecret;

}