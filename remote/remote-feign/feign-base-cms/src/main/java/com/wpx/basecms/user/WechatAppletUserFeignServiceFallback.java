package com.wpx.basecms.user;

import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatappletuser.WechatAppletUser;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * @description： wechatAppletUser feign remote fallback
 */
@Component
public class WechatAppletUserFeignServiceFallback implements FallbackFactory<WechatAppletUserFeignService> {

    /**
     * Returns an instance of the fallback appropriate for the given cause.
     *
     * @param cause cause of an exception.
     * @return fallback
     */
    @Override
    public WechatAppletUserFeignService create(Throwable cause) {
        return new WechatAppletUserFeignService() {
            /**
             * 获取微信小程序用户信息
             *
             * @param wechatAppletUserId 微信小程序用户ID
             * @return ResultVO<WechatAppletUser>
             */
            @Override
            public ResultVO<WechatAppletUser> getWechatAppletUser(Long wechatAppletUserId) {
                return null;
            }

            /**
             * 获取微信小程序用户信息
             *
             * @param wechatAppletUserIds 微信小程序用户ID集合
             * @return ResultVO<List < WechatAppletUser>>
             */
            @Override
            public ResultVO<List<WechatAppletUser>> listWechatAppletUser(Set<Long> wechatAppletUserIds) {
                return null;
            }

            /**
             * 获取微信小程序用户信息
             *
             * @param userIds 用户ID集合
             * @return ResultVO<List < WechatAppletUser>>
             */
            @Override
            public ResultVO<List<WechatAppletUser>> listWechatAppletUserByUserIds(Set<Long> userIds) {
                return null;
            }
        };
    }

}
