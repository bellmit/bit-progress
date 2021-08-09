package com.wpx.model.login;

/**
 * @author wpx
 */
public class LoginDTO {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户权限，暂时是单一权限
     */
    private String role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LoginDTO() {
    }

    public LoginDTO(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
