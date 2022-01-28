package com.wpx.auth.base;

import java.io.Serializable;

/**
 * @author 不会飞的小鹏
 * 授权信息
 */
public class AuthMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

    private String roleKey;

    private String permissions;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public AuthMsg() {
    }

}
