package com.wpx.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.mapper.user.WechatOaUserMapper;
import com.wpx.model.user.wechatoauser.WechatOaUser;
import com.wpx.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
* <p>
    * 微信公众号用户信息 服务类
    * </p>
*
* @author 不会飞的小鹏
* @since 2021-08-14
*/
@Service
@Slf4j
public class WechatOaUserService extends ServiceImpl<WechatOaUserMapper, WechatOaUser> {

    /**
     * 获取微信公众号用户信息
     *
     * @param wechatOaUserId  微信公众号用户ID
     * @return WechatOaUser
     */
    public WechatOaUser getWechatOaUserById(Long wechatOaUserId) {
        return getById(wechatOaUserId);
    }

    /**
     * 获取微信公众号用户信息集合
     *
     * @param wechatOaUserIds  微信公众号用户ID集合
     * @return List<WechatOaUser>
     */
    public List<WechatOaUser> listWechatOaUserByIds(Set<Long> wechatOaUserIds) {
        if (CollectionUtils.isEmpty(wechatOaUserIds)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<WechatOaUser> lambda = new QueryWrapper<WechatOaUser>().lambda();
        lambda.in(WechatOaUser::getWechatOaUserId, wechatOaUserIds);
        return list(lambda);
    }

    /**
     * 获取微信公众号用户信息集合
     *
     * @param userIds  用户ID集合
     * @return List<WechatOaUser>
     */
    public List<WechatOaUser> listWechatOaUserByUserIds(Set<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<WechatOaUser> lambda = new QueryWrapper<WechatOaUser>().lambda();
        lambda.in(WechatOaUser::getUserId, userIds);
        return list(lambda);
    }

}
