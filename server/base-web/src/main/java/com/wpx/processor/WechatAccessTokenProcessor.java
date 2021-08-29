package com.wpx.processor;

import com.wpx.constant.BaseRedisKeyPrefix;
import com.wpx.constant.BaseRedisLockKeyPrefix;
import com.wpx.exception.BaseException;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.exception.CommonException;
import com.wpx.exception.ExceptionMessage;
import com.wpx.model.AccessToken;
import com.wpx.util.Assert;
import com.wpx.util.RedisCacheUtils;
import com.wpx.util.RedisLockUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author 不会飞的小鹏
 */
@Component
@Slf4j
public class WechatAccessTokenProcessor {

    @Autowired
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private RedisLockUtils redisLockUtils;

    /**
     * 根据appSign获取accessToken
     *
     * @param appSign
     * @return accessToken
     */
    public String getAccessTokenByAppSign(String appSign) {
        String key = BaseRedisKeyPrefix.accessTokenKey(appSign);
        return redisCacheUtils.getForValue(key);
    }

    /**
     * 从微信端获取accessToken
     *
     * @param appSign
     */
    private String refreshAccessToken(String appSign, String appId, String appSecret) throws CommonException {
        String lock = BaseRedisLockKeyPrefix.wechatAccessTokenLock(appSign);
        String value = UUID.randomUUID().toString();
        boolean lockResult = redisLockUtils.lock(lock, value, 15);
        Assert.isTrue(lockResult, ExceptionMessage.ACQUIRE_LOCK_EXCEPTION);
        try {
            String key = BaseRedisKeyPrefix.accessTokenKey(appSign);
            AccessToken token = WechatProcessor.getAccessToken(appId, appSecret);
            String accessToken = token.getAccessToken();
            redisCacheUtils.setForValueTtl(key, accessToken, token.getExpiresIn());
            return accessToken;
        } catch (Exception e) {
            log.error("refreshWechatAccessToken error ", e);
            throw new BaseException(BaseExceptionMessage.REFRESH_ACCESSTOKEN_EXCEPTION);
        } finally {
            redisLockUtils.unlock(lock, value);
        }
    }

}
