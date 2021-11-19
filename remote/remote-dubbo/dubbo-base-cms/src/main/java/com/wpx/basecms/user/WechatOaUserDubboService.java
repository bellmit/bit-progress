package com.wpx.basecms.user;

import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatoauser.WechatOaUser;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * wechatOaUser feign remote
 */
public interface WechatOaUserDubboService {

    /**
     * 获取微信公众号用户信息
     *
     * @param wechatOaUserId  微信公众号用户ID
     * @return: ResultVO<WechatOaUser>
     */
    ResultVO<WechatOaUser> getWechatOaUser(Long wechatOaUserId);

    /**
     * 获取微信公众号用户信息
     *
     * @param wechatOaUserIds  微信公众号用户ID集合
     * @return: ResultVO<List<WechatOaUser>>
     */
    ResultVO<List<WechatOaUser>> listWechatOaUser(Set<Long> wechatOaUserIds);

    /**
     * 获取公众号微信用户信息
     *
     * @param userIds  用户ID集合
     * @return: ResultVO<List<WechatOaUser>>
     */
    ResultVO<List<WechatOaUser>> listWechatOaUserByUserIds(Set<Long> userIds);

}
