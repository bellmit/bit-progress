package com.wpx.redis;

import com.alibaba.fastjson.JSON;
import com.wpx.common.util.CollectionUtils;
import com.wpx.constant.RedisKeyPrefix;
import com.wpx.model.system.application.pojo.ApplicationItem;
import com.wpx.model.system.applicationtopic.pojo.ApplicationTopicItem;
import com.wpx.util.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class SystemRedisService {

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    /**
     * 获取验证码信息
     *
     * @param uuid
     */
    public String getCapText(String uuid) {
        String key = RedisKeyPrefix.captcha(uuid);
        return redisCacheUtil.getForValue(key);
    }

    /**
     * 保存验证码信息
     *
     * @param uuid
     * @param capText
     */
    public void setCapText(String uuid, String capText) {
        String key = RedisKeyPrefix.captcha(uuid);
        redisCacheUtil.setForValueTtl(key, capText, 5L, TimeUnit.MINUTES);
    }

    /**
     * 删除验证码信息
     *
     * @param uuid
     */
    public void deleteCaptcha(String uuid) {
        String key = RedisKeyPrefix.captcha(uuid);
        redisCacheUtil.delete(key);
    }

    /**
     * 将应用信息put到redis hash中
     *
     * @param item
     */
    public void putApplicationMessageToRedis(ApplicationItem item) {
        String key = RedisKeyPrefix.applicationMessage();
        redisCacheUtil.putForHash(key, item.getAppSign(), JSON.toJSONString(item));
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
        String key = RedisKeyPrefix.applicationMessage();
        redisCacheUtil.deleteForHash(key, appSigns.toArray());
    }

    /**
     * 将主题消息信息存放到redis中
     *
     * @param item
     */
    public void putApplicationTopicMessageToRedis(ApplicationTopicItem item) {
        String key = RedisKeyPrefix.applicationTopicMessage();
        redisCacheUtil.putForHash(key, item.getTopic(), JSON.toJSONString(item));
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
        String key = RedisKeyPrefix.applicationTopicMessage();
        redisCacheUtil.deleteForHash(key, topics.toArray());
    }
}
