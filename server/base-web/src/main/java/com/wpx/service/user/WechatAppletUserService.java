package com.wpx.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.mapper.user.WechatAppletUserMapper;
import com.wpx.model.user.wechatappletuser.WechatAppletUser;
import com.wpx.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
* <p>
    * 微信小程序用户信息 服务类
    * </p>
*
* @author 不会飞的小鹏
* @since 2021-08-14
*/
@Service
@Slf4j
public class WechatAppletUserService extends ServiceImpl<WechatAppletUserMapper, WechatAppletUser> {

    /**
     * 获取微信小程序用户信息
     *
     * @param wechatAppletUserId  微信小程序用户ID
     * @return: WechatAppletUser
     */
    public WechatAppletUser getWechatAppletUserById(Long wechatAppletUserId) {
        return getById(wechatAppletUserId);
    }

    /**
     * 获取微信小程序用户信息集合
     *
     * @param wechatAppletUserIds  微信小程序用户ID集合
     * @return List<WechatAppletUser>
     */
    public List<WechatAppletUser> listWechatAppletUserByIds(Set<Long> wechatAppletUserIds) {
        if (CollectionUtils.isEmpty(wechatAppletUserIds)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<WechatAppletUser> lambda = new QueryWrapper<WechatAppletUser>().lambda();
        lambda.in(WechatAppletUser::getWechatAppletUserId, wechatAppletUserIds);
        return list(lambda);
    }

    /**
     * 根据用户ID集合获取微信小程序用户信息集合
     *
     * @param userIds  用户ID集合
     * @return List<WechatAppletUser>
     */
    public List<WechatAppletUser> listWechatAppletUserByUserIds(Set<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<WechatAppletUser> lambda = new QueryWrapper<WechatAppletUser>().lambda();
        lambda.in(WechatAppletUser::getUserId, userIds);
        return list(lambda);
    }

}
