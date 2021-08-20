package com.wpx.rest.feign;

import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatappletuser.WechatAppletUser;
import com.wpx.service.user.WechatAppletUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * @description： wechatAppletUser
 */
@RestController
@RequestMapping("rest/baseWeb/wechatAppletUser")
public class WechatAppletUserFeignController {

    @Autowired
    private WechatAppletUserService wechatAppletUserService;

    /**
     * 获取微信用户信息
     *
     * @param wechatAppletUserId  微信用户ID
     * @return: ResultVO<WechatAppletUser>
     */
    @GetMapping
    ResultVO<WechatAppletUser> getWechatAppletUser(@RequestParam Long wechatAppletUserId) {
        return ResultVO.successData(wechatAppletUserService.getWechatAppletUserById(wechatAppletUserId));
    }

    /**
     * 获取微信用户信息
     *
     * @param wechatAppletUserIds  微信用户ID集合
     * @return: ResultVO<List<WechatAppletUser>>
     */
    @GetMapping("list")
    ResultVO<List<WechatAppletUser>> listWechatAppletUser(@RequestParam Set<Long> wechatAppletUserIds) {
        return ResultVO.successData(wechatAppletUserService.listWechatAppletUserByIds(wechatAppletUserIds));
    }

    /**
     * 获取微信用户信息
     *
     * @param userIds  用户ID集合
     * @return: ResultVO<List<WechatAppletUser>>
     */
    @GetMapping("list/userIds")
    ResultVO<List<WechatAppletUser>> listWechatAppletUserByUserIds(@RequestParam Set<Long> userIds) {
        return ResultVO.successData(wechatAppletUserService.listWechatAppletUserByUserIds(userIds));
    }

}
