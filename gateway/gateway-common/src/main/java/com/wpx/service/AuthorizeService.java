package com.wpx.service;

import com.wpx.model.login.AuthMsg;
import com.wpx.util.StringUtils;
import com.wpx.exception.envm.AuthException;
import com.wpx.model.result.AuthResult;
import com.wpx.manager.shiro.service.ShiroTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 不会飞的小鹏
 * 授权鉴权服务
 */
@Service
public class AuthorizeService {

    @Autowired
    private ShiroTokenService shiroTokenService;

    /**
     * Authorization认证开头是"token "
     */
    private static final String BEARER = "token ";

    /**
     * Authentication的值分割后的长度
     */
    private static final int AUTHENTICATION_SPIT_LENGTH = 2;

    /**
     * token的下标
     */
    private static final int TOKEN_INDEX = 1;

    /**
     * 检验是否有访问权限
     *
     * @param authentication
     * @return Result
     */
    public <T extends AuthMsg> AuthResult<T> checkToken(String authentication, Class<T> target) {
        String token;
        // 如果请求未携带token信息, 直接权限拒绝
        if (StringUtils.isEmpty(authentication) || !authentication.startsWith(BEARER)
                || Objects.isNull(token = getToken(authentication))) {
            AuthResult<T> authResult = new AuthResult<>();
            authResult.setResult(false);
            authResult.setAuthException(AuthException.AUTH_TOKEN_EMPTY);
            return authResult;
        }
        return shiroTokenService.checkToken(token, target);
    }

    /**
     * 获取 Authorization header 的 token 值
     *
     * @param authorizationHeader
     */
    private String getToken(String authorizationHeader) {
        String[] authTokens = authorizationHeader.split(StringUtils.SPACE);
        if (authTokens.length < AUTHENTICATION_SPIT_LENGTH) {
            return null;
        }
        return authTokens[TOKEN_INDEX];
    }

}
