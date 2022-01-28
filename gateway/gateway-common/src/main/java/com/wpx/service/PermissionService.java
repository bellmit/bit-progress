package com.wpx.service;

import com.wpx.auth.base.AuthMsg;
import com.wpx.auth.base.AuthResult;

/**
 * @author wpx
 * 权限匹配服务
 */
public interface PermissionService {

    /**
     * 检验接口权限
     * 通过 method+url 获取接口的权限信息
     *
     * @param authResult 鉴权结果
     * @param method     方法
     * @param url        接口url
     */
    void authorizePermission(AuthResult<AuthMsg> authResult, String method, String url);

}
