package com.wpx.constant;

/**
 * @author 不会飞的小鹏
 * @desc: redis lock key
 */
public class BaseRedisLockKeyPrefix {

    /**
     * 获取微信accessToken 的 redis lock
     */
    public static String wechatAccessTokenLock(String appSign) {
        return "WechatAccessTokenLock:" + appSign;
    }

}
