package com.wpx.model.result;

import com.wpx.exception.envm.AuthException;

/**
 * @author wpx
 * Created on 2021/1/26 11:20
 */
public class AuthResult {

    private Boolean result;

    private String userId;

    private String role;

    private AuthException authException;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

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

    public AuthException getAuthException() {
        return authException;
    }

    public void setAuthException(AuthException authException) {
        this.authException = authException;
    }
}
