package com.wpx.manager.wechat;

import com.wpx.constant.BaseRedisKeyPrefix;
import com.wpx.constant.BaseRedisLockKeyPrefix;
import com.wpx.exception.BaseException;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.exception.CommonException;
import com.wpx.exception.ExceptionMessage;
import com.wpx.model.AccessToken;
import com.wpx.model.app.app.envm.AppTypeEnum;
import com.wpx.model.login.WechatPhoneDTO;
import com.wpx.processor.WechatProcessor;
import com.wpx.service.user.UserService;
import com.wpx.service.user.WechatAppletUserService;
import com.wpx.service.user.WechatOaUserService;
import com.wpx.util.Assert;
import com.wpx.util.RedisCacheUtils;
import com.wpx.util.RedisLockUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author 不会飞的小鹏
 * @desc: 微信相关服务的manager层
 */
@Service
@Slf4j
public class WechatService {

    @Autowired
    private RedisCacheUtils redisCacheUtils;
    @Autowired
    private RedisLockUtils redisLockUtils;

    @Autowired
    private UserService userService;
    @Autowired
    private WechatAppletUserService wechatAppletUserService;
    @Autowired
    private WechatOaUserService wechatOaUserService;

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

    /**
     * 更新用户的手机号码
     *
     * @param wechatPhoneDTO
     * @param userId
     */
    public void updatePhone(WechatPhoneDTO wechatPhoneDTO, Long userId) {
        AppTypeEnum appType = userService.getUserAppTypeById(userId);
        String encryptedData = wechatPhoneDTO.getEncryptedData();
        String iv = wechatPhoneDTO.getIv();
        switch (appType) {
            case WECHAT_APPLET: {
                wechatAppletUserService.updatePhone(encryptedData, iv, userId);
                break;
            }
            case WECHAT_OA: {
                wechatOaUserService.updatePhone(encryptedData, iv, userId);
                break;
            }
            default: {
                throw new BaseException(BaseExceptionMessage.NOT_WECHATUSER_EXCEPTION);
            }
        }
    }

}
