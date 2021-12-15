package com.wpx.baseweb.user;

import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatappletuser.WechatAppletUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * wechatAppletUser feign remote
 */
@FeignClient(name = "main-base-web", path = "rest/baseWeb/wechatAppletUser", fallbackFactory = WechatAppletUserFeignServiceFallback.class)
public interface WechatAppletUserFeignService {

    /**
     * 获取微信小程序用户信息
     *
     * @param wechatAppletUserId  微信小程序用户ID
     * @return ResultVO<WechatAppletUser>
     */
    @GetMapping
    ResultVO<WechatAppletUser> getWechatAppletUser(@RequestParam Long wechatAppletUserId);

    /**
     * 获取微信小程序用户信息
     *
     * @param wechatAppletUserIds  微信小程序用户ID集合
     * @return ResultVO<List<WechatAppletUser>>
     */
    @GetMapping("list")
    ResultVO<List<WechatAppletUser>> listWechatAppletUser(@RequestParam Set<Long> wechatAppletUserIds);

    /**
     * 获取微信小程序用户信息
     *
     * @param userIds  用户ID集合
     * @return ResultVO<List<WechatAppletUser>>
     */
    @GetMapping("list/userIds")
    ResultVO<List<WechatAppletUser>> listWechatAppletUserByUserIds(@RequestParam Set<Long> userIds);

}
