package com.wpx.model.login;

import java.io.Serializable;

/**
 * @Author: 不会飞的小鹏
 * @Description: 登录VO
 */
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" + "userId=" + userId + ", token=" + token + "}";
    }

}
