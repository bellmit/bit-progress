package com.wpx.model.login;

import java.io.Serializable;

/**
 * @Author: 不会飞的小鹏
 * @Description: token检查
 */
public class TokenCheckDTO implements Serializable {

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
        return "TokenCheckDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}
