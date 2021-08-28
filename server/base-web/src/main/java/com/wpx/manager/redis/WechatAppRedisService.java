package com.wpx.manager.redis;

import com.wpx.constant.BaseRedisKeyPrefix;
import com.wpx.model.app.wechatapp.pojo.WechatAppRO;
import com.wpx.util.JsonUtils;
import com.wpx.util.RedisCacheUtils;
import com.wpx.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 不会飞的小鹏
 * @desc: 微信应用redis服务
 */
@Component
public class WechatAppRedisService {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    /**
     * 刷新redis中wechatApp信息
     *
     * @param wechatAppRO
     */
    public void refreshWechatApp(WechatAppRO wechatAppRO) {
        String key = BaseRedisKeyPrefix.wechatAppKey();
        String value = JsonUtils.serializeObject(wechatAppRO);
        redisCacheUtils.putForHash(key, wechatAppRO.getAppSign(), value);
    }

    /**
     * 刷新redis中wechatApp信息
     *
     * @param appSign
     */
    public WechatAppRO getWechatApp(String appSign) {
        String key = BaseRedisKeyPrefix.wechatAppKey();
        String value = redisCacheUtils.getForHash(key, appSign);
        return StringUtils.isEmpty(value) ? null : JsonUtils.deserializeObject(value, WechatAppRO.class);
    }

}
