package com.wpx.baseweb.user;

import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatoauser.WechatOaUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * @description: wechatOaUser feign remote
 */
@FeignClient(name = "main-base-web", path = "rest/baseWeb/wechatOaUser", fallbackFactory = WechatOaUserFeignServiceFallback.class)
public interface WechatOaUserFeignService {

    /**
     * 获取微信公众号用户信息
     *
     * @param wechatOaUserId  微信公众号用户ID
     * @return: ResultVO<WechatOaUser>
     */
    @GetMapping
    ResultVO<WechatOaUser> getWechatOaUser(@RequestParam Long wechatOaUserId);

    /**
     * 获取微信公众号用户信息
     *
     * @param wechatOaUserIds  微信公众号用户ID集合
     * @return: ResultVO<List<WechatOaUser>>
     */
    @GetMapping("list")
    ResultVO<List<WechatOaUser>> listWechatOaUser(@RequestParam Set<Long> wechatOaUserIds);

    /**
     * 获取公众号微信用户信息
     *
     * @param userIds  用户ID集合
     * @return: ResultVO<List<WechatOaUser>>
     */
    @GetMapping("list/userIds")
    ResultVO<List<WechatOaUser>> listWechatOaUserByUserIds(@RequestParam Set<Long> userIds);

}
