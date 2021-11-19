package com.wpx.rest.feign;

import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatuser.WechatUser;
import com.wpx.service.user.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * wechatUser feign remote
 */
@RestController
@RequestMapping("rest/baseWeb/wechatUser")
public class WechatUserFeignController {

    @Autowired
    private WechatUserService wechatUserService;

    /**
     * 获取微信用户信息
     *
     * @param wechatUserId  微信用户ID
     * @return: ResultVO<WechatUser>
     */
    @GetMapping
    public ResultVO<WechatUser> getWechatUser(@RequestParam Long wechatUserId) {
        return ResultVO.successData(wechatUserService.getWechatUserById(wechatUserId));
    }

    /**
     * 获取微信用户信息
     *
     * @param wechatUserIds  微信用户ID集合
     * @return: ResultVO<List<WechatUser>>
     */
    @GetMapping("list")
    public ResultVO<List<WechatUser>> listWechatUser(@RequestParam Set<Long> wechatUserIds) {
        return ResultVO.successData(wechatUserService.listWechatUser(wechatUserIds));
    }

}
