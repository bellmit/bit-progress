package com.wpx.service;

import com.wpx.util.StringUtils;
import com.wpx.shiro.service.ShiroTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wpx
 * Created on 2021/1/27 17:44
 * @description：
 */
@Service
public class LoginService {

    @Autowired
    private ShiroTokenService shiroTokenService;

    /**
     * 用户进行登录
     *
     * @param    userId
     * @param    role
     * @return   String
     */
    public String login(String userId, String role) {
        return StringUtils.isEmpty(role) ? shiroTokenService.login(userId) : shiroTokenService.login(userId, role);
    }

    /**
     * 用户进行登出
     *
     * @param    userId
     */
    public void logout(String userId) {
        shiroTokenService.logout(userId);
    }

    /**
     * 从用户的token解析用户的userId
     *
     * @param token
     */
    public String getUserIdInToken(String token) {
        return shiroTokenService.getUserIdInToken(token);
    }

    /**
     * 加密token
     *
     * @param rawToken
     */
    public String encodeToken(String rawToken) {
        return shiroTokenService.encodeToken(rawToken);
    }

    /**
     * 解密token
     *
     * @param token
     */
    public String decodeToken(String token) {
        return shiroTokenService.decodeToken(token);
    }

    /**
     * 检验token是否有效
     *
     * @param token
     */
    public Boolean checkToken(String token) {
        return shiroTokenService.checkToken(token).getResult();
    }
}
