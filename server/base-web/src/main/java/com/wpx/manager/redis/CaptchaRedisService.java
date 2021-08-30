package com.wpx.manager.redis;

import com.wpx.constant.BaseRedisKeyPrefix;
import com.wpx.util.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 不会飞的小鹏
 */
@Service
public class CaptchaRedisService {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    /**
     * 通过手机号码获取短信验证码
     *
     * @param phone
     */
    public String getSmsCaptcha(String phone) {
        String captchaKey = BaseRedisKeyPrefix.smsCaptcha(phone);
        return redisCacheUtils.getForValue(captchaKey);
    }

}
