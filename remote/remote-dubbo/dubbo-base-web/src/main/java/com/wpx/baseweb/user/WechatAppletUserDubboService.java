package com.wpx.baseweb.user;

import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatappletuser.WechatAppletUser;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * @description: wechatAppletUser feign remote
 */
public interface WechatAppletUserDubboService {

    /**
     * 获取微信用户信息
     *
     * @param wechatAppletUserId  微信用户ID
     * @return: ResultVO<WechatAppletUser>
     */
    ResultVO<WechatAppletUser> getWechatAppletUser(Long wechatAppletUserId);

    /**
     * 获取微信用户信息
     *
     * @param wechatAppletUserIds  微信用户ID集合
     * @return: ResultVO<List<WechatAppletUser>>
     */
    ResultVO<List<WechatAppletUser>> listWechatAppletUser(Set<Long> wechatAppletUserIds);

    /**
     * 获取微信用户信息
     *
     * @param userIds  用户ID集合
     * @return: ResultVO<List<WechatAppletUser>>
     */
    ResultVO<List<WechatAppletUser>> listWechatAppletUserByUserIds(Set<Long> userIds);

}
