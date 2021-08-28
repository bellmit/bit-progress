package com.wpx.processor;

import com.wpx.constant.BaseRedisKeyPrefix;
import com.wpx.model.AccessToken;
import com.wpx.util.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 不会飞的小鹏
 */
@Component
public class WechatAccessTokenProcessor {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

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
    private String refreshAccessToken(String appSign, String appId, String appSecret) {
        String key = BaseRedisKeyPrefix.accessTokenKey(appSign);
        AccessToken token = WechatProcessor.getAccessToken(appId, appSecret);
        String accessToken = token.getAccessToken();
        redisCacheUtils.setForValueTtl(key, accessToken, token.getExpiresIn());
        return accessToken;
    }

}
