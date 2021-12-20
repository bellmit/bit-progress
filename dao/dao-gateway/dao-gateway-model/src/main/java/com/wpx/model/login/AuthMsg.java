package com.wpx.model.login;

import java.io.Serializable;

/**
 * @author 不会飞的小鹏
 * 授权信息
 */
public class AuthMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthMsg() {
    }

}
