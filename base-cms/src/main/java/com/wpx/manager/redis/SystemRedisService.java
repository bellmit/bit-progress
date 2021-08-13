package com.wpx.manager.redis;

import com.wpx.util.CollectionUtils;
import com.wpx.constant.BaseRedisKeyPrefix;
import com.wpx.util.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class SystemRedisService {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    /**
     * 获取验证码信息
     *
     * @param uuid
     */
    public String getCapText(String uuid) {
        String key = BaseRedisKeyPrefix.captcha(uuid);
        return redisCacheUtils.getForValue(key);
    }

    /**
     * 保存验证码信息
     *
     * @param uuid
     * @param capText
     */
    public void setCapText(String uuid, String capText) {
        String key = BaseRedisKeyPrefix.captcha(uuid);
        redisCacheUtils.setForValueTtl(key, capText, 5L, TimeUnit.MINUTES);
    }

    /**
     * 删除验证码信息
     *
     * @param uuid
     */
    public void deleteCaptcha(String uuid) {
        String key = BaseRedisKeyPrefix.captcha(uuid);
        redisCacheUtils.delete(key);
    }

    /**
     * 将应用信息从缓存中删除
     *
     * @param appSigns
     */
    public void deleteApplicationMessageForRedis(Set<String> appSigns) {
        if (CollectionUtils.isEmpty(appSigns)) {
            return;
        }
        // 删除缓存中的应用信息
        String key = BaseRedisKeyPrefix.applicationMessage();
        redisCacheUtils.deleteForHash(key, appSigns.toArray());
    }

    /**
     * 从缓存中删除应用主题消息信息
     *
     * @param topics
     */
    public void deleteApplicationTopicMessageForRedis(Set<String> topics) {
        if (CollectionUtils.isEmpty(topics)) {
            return;
        }
        String key = BaseRedisKeyPrefix.applicationTopicMessage();
        redisCacheUtils.deleteForHash(key, topics.toArray());
    }
}
