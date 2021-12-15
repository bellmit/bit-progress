package com.wpx.baseweb.user;

import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatoauser.WechatOaUser;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * @description： wechatOaUser feign remote fallback
 */
public class WechatOaUserFeignServiceFallback implements FallbackFactory<WechatOaUserFeignService> {

    /**
     * Returns an instance of the fallback appropriate for the given cause.
     *
     * @param cause cause of an exception.
     * @return fallback
     */
    @Override
    public WechatOaUserFeignService create(Throwable cause) {
        return new WechatOaUserFeignService() {
            /**
             * 获取微信公众号用户信息
             *
             * @param wechatOaUserId 微信公众号用户ID
             * @return ResultVO<WechatOaUser>
             */
            @Override
            public ResultVO<WechatOaUser> getWechatOaUser(Long wechatOaUserId) {
                return null;
            }

            /**
             * 获取微信公众号用户信息
             *
             * @param wechatOaUserIds 微信公众号用户ID集合
             * @return ResultVO<List < WechatOaUser>>
             */
            @Override
            public ResultVO<List<WechatOaUser>> listWechatOaUser(Set<Long> wechatOaUserIds) {
                return null;
            }

            /**
             * 获取公众号微信用户信息
             *
             * @param userIds 用户ID集合
             * @return ResultVO<List < WechatOaUser>>
             */
            @Override
            public ResultVO<List<WechatOaUser>> listWechatOaUserByUserIds(Set<Long> userIds) {
                return null;
            }
        };
    }

}
