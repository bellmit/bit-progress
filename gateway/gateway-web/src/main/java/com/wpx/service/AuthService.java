package com.wpx.service;

import com.wpx.manager.shiro.service.ShiroTokenService;
import com.wpx.model.BooleanVO;
import com.wpx.model.login.AuthWebMsg;
import com.wpx.model.login.LoginVO;
import com.wpx.model.result.AuthResult;
import com.wpx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wpx
 * Created on 2021/1/27 17:44
 * 
 */
@Service
public class AuthService {

    @Autowired
    private ShiroTokenService shiroTokenService;

    /**
     * 用户进行登录
     *
     * @param    userId
     * @param    authMsg
     * @return   String
     */
    public LoginVO login(String userId, AuthWebMsg authMsg) {
        LoginVO loginVO = new LoginVO();
        String token = shiroTokenService.login(userId, authMsg);
        loginVO.setUserId(Long.parseLong(userId));
        loginVO.setToken(token);
        return loginVO;
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
    public LoginVO getUserIdInToken(String token) {
        LoginVO loginVO = new LoginVO();
        String userId = shiroTokenService.getUserIdInToken(token);
        loginVO.setUserId(Long.parseLong(userId));
        loginVO.setToken(token);
        return loginVO;
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
    public AuthResult<AuthWebMsg> checkToken(String token) {
        return shiroTokenService.checkToken(token, AuthWebMsg.class);
    }
}
