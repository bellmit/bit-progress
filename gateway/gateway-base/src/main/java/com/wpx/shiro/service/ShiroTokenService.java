package com.wpx.shiro.service;

import com.wpx.common.util.StringUtils;
import com.wpx.exception.envm.AuthException;
import com.wpx.model.result.AuthResult;
import com.wpx.shiro.base.ShiroModuleProperties;
import com.wpx.shiro.base.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 采用shiro模式的token校验，而非继承了shiro
 * 相较于集成鉴权框架更加轻量，只进行了token的生成和校验，而不干涉登录的逻辑
 *
 * @author wpx
 **/
@Service
public class ShiroTokenService {

    private final Logger logger = LoggerFactory.getLogger(ShiroTokenService.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ShiroModuleProperties shiroModuleProperties;

    @Autowired
    private TokenUtil tokenUtil;

    private static final String baseString = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 登录
     *
     * @param userId
     */
    public String login(String userId) {
        return updateToken(userId, null);
    }

    /**
     * 登录
     *
     * @param userId
     * @param role
     * @return
     */
    public String login(String userId, String role) {
        return updateToken(userId, role);
    }

    /**
     * 更新token
     *
     * @param userId
     * @param role
     */
    private String updateToken(String userId, String role) {
        String salt = randomString(5);

        String tokenPrefix = shiroModuleProperties.getTokenPrefix();
        String tokenName = shiroModuleProperties.getTokenName();
        int cacheDays = shiroModuleProperties.getCacheDays();
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        String content = generateEncodeContent(userId, role, salt);
        String token = tokenUtil.encode(content);
        logger.info("setToken userId: [{}] = token:[{}]", userId, token);
        stringRedisTemplate.opsForValue().set(tokenPrefix + tokenName + userId, token, cacheDays, TimeUnit.DAYS);
        return token;
    }

    /**
     * 生成需要加密的content
     */
    private String generateEncodeContent(String userId, String role, String salt) {
        return userId + "." + (StringUtils.isEmpty(role) ? "-1" : role) + "." + salt;
    }

    /**
     * 登出
     *
     * @param userId
     */
    public void logout(String userId) {
        if (!shiroModuleProperties.isMultiLogin()) {
            String tokenPrefix = shiroModuleProperties.getTokenPrefix();
            String tokenName = shiroModuleProperties.getTokenName();
            stringRedisTemplate.delete(tokenPrefix + tokenName + userId);
        }
    }

    /**
     * 更新用户角色
     *
     * @param userId
     * @param role
     */
    public void updateRole(String userId, String role) {
        String tokenPrefix = shiroModuleProperties.getTokenPrefix();
        String tokenName = shiroModuleProperties.getTokenName();
        Integer cacheDays = shiroModuleProperties.getCacheDays();
        if (!StringUtils.isEmpty(role)) {
            stringRedisTemplate.delete(tokenPrefix + tokenName + userId);
            String token = tokenUtil.encode(userId + "." + role);
            stringRedisTemplate.opsForValue().set(tokenPrefix + tokenName + userId, token, cacheDays, TimeUnit.DAYS);
        }
    }

    /**
     * 校验token
     *
     * @param    token
     * @return   Result
     */
    public AuthResult checkToken(String token) {
        AuthResult auth = new AuthResult();
        String userIdTokenRole = this.tokenUtil.decode(token);
        String userId;
        String role;
        try {
            String[] split = userIdTokenRole.split("\\.");
            userId = split[0];
            role = split[1];
            auth.setUserId(userId);
            auth.setRole(role);
        } catch (Exception var7) {
            auth.setResult(false);
            auth.setAuthException(AuthException.AUTH_TOKEN_WRONG);
            return auth;
        }
        String tokenPrefix = shiroModuleProperties.getTokenPrefix();
        String tokenName = shiroModuleProperties.getTokenName();
        String redisToken = stringRedisTemplate.opsForValue().get(tokenPrefix + tokenName + userId);
        boolean result = StringUtils.nonEmpty(redisToken) && redisToken.equals(token);
        auth.setResult(result);
        if (!result) {
            auth.setAuthException(AuthException.AUTH_TOKEN_WRONG);
        }
        return auth;
    }

    /**
     * 生成随机数
     *
     * @param length
     */
    public static String randomString(int length) {
        if (StringUtils.isEmpty(baseString)) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(length);
            if (length < 1) {
                length = 1;
            }

            int baseLength = baseString.length();

            for (int i = 0; i < length; ++i) {
                int number = ThreadLocalRandom.current().nextInt(baseLength);
                sb.append(baseString.charAt(number));
            }

            return sb.toString();
        }
    }

    /**
     * 从token中解析出userID
     *
     * @param token
     */
    public String getUserIdInToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        String userIdTokenRole = decodeToken(token);
        String userId;
        try {
            String[] split = userIdTokenRole.split("\\.");
            userId = split[0];
        } catch (Exception var7) {
            logger.error("token decode error", var7);
            return null;
        }
        return userId;
    }

    /**
     * 加密token
     *
     * @param rawToken
     */
    public String encodeToken(String rawToken) {
        return tokenUtil.encode(rawToken);
    }

    /**
     * 解密token
     *
     * @param token
     */
    public String decodeToken(String token) {
        return tokenUtil.decode(token);
    }
}
