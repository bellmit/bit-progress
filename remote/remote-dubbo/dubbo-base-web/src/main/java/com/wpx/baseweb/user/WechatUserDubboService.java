package com.wpx.baseweb.user;

import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatuser.WechatUser;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * @description： wechatUser dubbo remote
 */
public interface WechatUserDubboService {

    /**
     * 获取微信用户信息
     *
     * @param wechatUserId  微信用户ID
     * @return ResultVO<WechatUser>
     */
    ResultVO<WechatUser> getWechatUser(Long wechatUserId);

    /**
     * 获取微信用户信息
     *
     * @param wechatUserIds  微信用户ID集合
     * @return ResultVO<List<WechatUser>>
     */
    ResultVO<List<WechatUser>> listWechatUser(Set<Long> wechatUserIds);

}
