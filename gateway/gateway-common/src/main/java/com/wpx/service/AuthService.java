package com.wpx.service;

import com.wpx.util.StringUtils;
import com.wpx.exception.envm.AuthException;
import com.wpx.model.result.AuthResult;
import com.wpx.manager.shiro.service.ShiroTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 不会飞的小鹏
 */
@Service
public class AuthService {

    @Autowired
    private ShiroTokenService shiroTokenService;

    /**
     * Authorization认证开头是"token "
     */
    private static final String BEARER = "token ";

    /**
     * 检验是否有访问权限
     *
     * @param authentication
     * @return: Result
     */
    public AuthResult checkToken(String authentication) {
        String token;
        // 如果请求未携带token信息, 直接权限拒绝
        if (StringUtils.isEmpty(authentication) || !authentication.startsWith(BEARER)
                || Objects.isNull(token = getToken(authentication))) {
            AuthResult authResult = new AuthResult();
            authResult.setResult(false);
            authResult.setAuthException(AuthException.AUTH_TOKEN_EMPTY);
            return authResult;
        }
        return shiroTokenService.checkToken(token);
    }

    /**
     * 获取 Authorization header 的 token 值
     *
     * @param authorizationHeader
     */
    private String getToken(String authorizationHeader) {
        String[] authTokens = authorizationHeader.split(StringUtils.SPACE);
        if (authTokens.length < 2) {
            return null;
        }
        return authTokens[1];
    }

}
