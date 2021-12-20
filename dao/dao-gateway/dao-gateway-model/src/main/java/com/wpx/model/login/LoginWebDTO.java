package com.wpx.model.login;

import java.io.Serializable;

/**
 * @author 不会飞的小鹏
 * 登录DTO
 */
public class LoginWebDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户权限，暂时是单一权限
     */
    private AuthWebMsg authMsg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AuthWebMsg getAuthMsg() {
        return authMsg;
    }

    public void setAuthMsg(AuthWebMsg authMsg) {
        this.authMsg = authMsg;
    }

    public LoginWebDTO() {
    }

    public LoginWebDTO(String userId, AuthWebMsg authMsg) {
        this.userId = userId;
        this.authMsg = authMsg;
    }

    @Override
    public String toString() {
        return "{" + "userId=" + userId + ", role=" + authMsg + "}";
    }

}
