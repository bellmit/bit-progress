package com.wpx.model.login;

/**
 * @author wpx
 */
public class LogoutDTO {

    /**
     * 用户ID
     */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LogoutDTO() {
    }

    public LogoutDTO(String userId) {
        this.userId = userId;
    }

}
