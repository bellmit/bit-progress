package com.wpx.auth.base;

import java.io.Serializable;
import java.util.Map;

/**
 * @author 不会飞的小鹏
 * 授权信息
 */
public class AuthMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户的token
     */
    private String token;

    /**
     * 用户的角色标识
     */
    private String roleKey;

    /**
     * 用户的权限字符
     */
    private String permissions;

    /**
     * 一些补充参数
     */
    private Map<String, String> params;

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

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public AuthMsg() {
    }

}
