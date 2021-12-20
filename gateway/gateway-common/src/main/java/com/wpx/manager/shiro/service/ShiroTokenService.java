package com.wpx.manager.shiro.service;

import com.alibaba.fastjson.JSON;
import com.wpx.model.login.AuthMsg;
import com.wpx.util.StringUtils;
import com.wpx.exception.envm.AuthException;
import com.wpx.model.result.AuthResult;
import com.wpx.manager.shiro.base.ShiroModuleProperties;
import com.wpx.manager.shiro.base.TokenUtil;
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

    private static final String BASE_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 登录
     *
     * @param userId
     */
    public <T extends AuthMsg> String login(String userId, T authMsg) {
        return updateToken(userId, authMsg);
    }

    /**
     * 更新token
     *
     * @param userId
     * @param authMsg
     */
    private <T extends AuthMsg> String updateToken(String userId, T authMsg) {
        String salt = randomString(5);
        String tokenPrefix = shiroModuleProperties.getTokenPrefix();
        String tokenName = shiroModuleProperties.getTokenName();
        int cacheDays = shiroModuleProperties.getCacheDays();
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        String content = generateEncodeContent(userId, salt);
        String token = tokenUtil.encode(content);
        logger.info("setToken userId: [{}] = token:[{}]", userId, token);
        if (authMsg == null) {
            authMsg = (T) new AuthMsg();
        }
        authMsg.setToken(token);
        String value = JSON.toJSONString(authMsg);
        stringRedisTemplate.opsForValue().set(tokenPrefix + tokenName + userId, value, cacheDays, TimeUnit.DAYS);
        return token;
    }

    /**
     * 生成需要加密的content
     */
    private String generateEncodeContent(String userId, String salt) {
        return userId + "." + salt;
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
     * 校验token
     *
     * @param    token
     * @return   Result
     */
    public <T extends AuthMsg> AuthResult<T> checkToken(String token, Class<T> target) {
        AuthResult<T> auth = new AuthResult<>();
        String userIdTokenRole = this.tokenUtil.decode(token);
        String userId;
        try {
            String[] split = userIdTokenRole.split("\\.");
            userId = split[0];
            auth.setUserId(userId);
        } catch (Exception var7) {
            auth.setResult(false);
            auth.setAuthException(AuthException.AUTH_TOKEN_WRONG);
            return auth;
        }
        String tokenPrefix = shiroModuleProperties.getTokenPrefix();
        String tokenName = shiroModuleProperties.getTokenName();
        String value = stringRedisTemplate.opsForValue().get(tokenPrefix + tokenName + userId);
        T authMsg = JSON.parseObject(value, target);
        String redisToken = authMsg.getToken();
        auth.setAuthMsg(authMsg);
        boolean result = StringUtils.nonEmpty(redisToken) && token.equals(redisToken);
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
        if (StringUtils.isEmpty(BASE_STRING)) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(length);
            if (length < 1) {
                length = 1;
            }

            int baseLength = BASE_STRING.length();

            for (int i = 0; i < length; ++i) {
                int number = ThreadLocalRandom.current().nextInt(baseLength);
                sb.append(BASE_STRING.charAt(number));
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
