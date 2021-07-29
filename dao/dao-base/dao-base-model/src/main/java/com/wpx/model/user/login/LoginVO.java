package com.wpx.model.user.login;

public class LoginVO {

    /**
     * 用户登录后的token
     */
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
