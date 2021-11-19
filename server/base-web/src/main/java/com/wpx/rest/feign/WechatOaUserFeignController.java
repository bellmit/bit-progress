package com.wpx.rest.feign;

import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatoauser.WechatOaUser;
import com.wpx.service.user.WechatOaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * wechatOaUser feign remote controller
 */
@RestController
@RequestMapping("rest/baseWeb/wechatOaUser")
public class WechatOaUserFeignController {

    @Autowired
    private WechatOaUserService wechatOaUserService;

    /**
     * 获取微信公众号用户信息
     *
     * @param wechatOaUserId  微信公众号用户ID
     * @return: ResultVO<WechatOaUser>
     */
    @GetMapping
    ResultVO<WechatOaUser> getWechatOaUser(@RequestParam Long wechatOaUserId) {
        return ResultVO.successData(wechatOaUserService.getWechatOaUserById(wechatOaUserId));
    }

    /**
     * 获取微信公众号用户信息
     *
     * @param wechatOaUserIds  微信公众号用户ID集合
     * @return: ResultVO<List<WechatOaUser>>
     */
    @GetMapping("list")
    ResultVO<List<WechatOaUser>> listWechatOaUser(@RequestParam Set<Long> wechatOaUserIds) {
        return ResultVO.successData(wechatOaUserService.listWechatOaUserByIds(wechatOaUserIds));
    }

    /**
     * 获取公众号微信用户信息
     *
     * @param userIds  用户ID集合
     * @return: ResultVO<List<WechatOaUser>>
     */
    @GetMapping("list/userIds")
    ResultVO<List<WechatOaUser>> listWechatOaUserByUserIds(@RequestParam Set<Long> userIds) {
        return ResultVO.successData(wechatOaUserService.listWechatOaUserByUserIds(userIds));
    }

}
