package com.wpx.model.system.login;

public class LoginVO {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginVO(String token) {
        this.token = token;
    }

    public static LoginVO loginSuccessful(String token) {
        return new LoginVO(token);
    }

}
