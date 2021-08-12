package com.wpx.model.login;

import java.io.Serializable;

/**
 * @Author: 不会飞的小鹏
 * @Description: 检查token的DTO
 */
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{" + "token=" + token + "}";
    }

}
