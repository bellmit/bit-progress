package com.wpx.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.WechatAppletUserMapper;
import com.wpx.model.app.app.envm.AppTypeEnum;
import com.wpx.model.user.login.WechatLoginDTO;
import com.wpx.model.user.wechatappletuser.WechatAppletUser;
import com.wpx.util.Assert;
import com.wpx.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 微信小程序用户信息 服务类
 * </p>
 *
 * @author 不会飞的小鹏
 * create on 2021-08-14
 */
@Service
@Slf4j
public class WechatAppletUserService extends ServiceImpl<WechatAppletUserMapper, WechatAppletUser> {

    @Autowired
    private UserService userService;

    /**
     * 获取微信小程序用户信息
     *
     * @param wechatAppletUserId 微信小程序用户ID
     * @return: WechatAppletUser
     */
    public WechatAppletUser getWechatAppletUserById(Long wechatAppletUserId) {
        return getById(wechatAppletUserId);
    }

    /**
     * 获取微信小程序用户信息集合
     *
     * @param wechatAppletUserIds 微信小程序用户ID集合
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
     * @param userIds 用户ID集合
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

    /**
     * 更新小程序用户
     *
     * @param wechatLoginDTO
     * @param openId
     * @param unionId
     * @param wechatUserId
     * @param appId
     * @param appSign
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public Long updateUser(WechatLoginDTO wechatLoginDTO, String openId, String unionId, Long wechatUserId,
                           Long appId, String appSign) {

        LambdaQueryChainWrapper<WechatAppletUser> query = lambdaQuery().eq(WechatAppletUser::getOpenId, openId)
                .eq(WechatAppletUser::getUnionId, unionId)
                .eq(WechatAppletUser::getAppId, appId)
                .eq(WechatAppletUser::getAppSign, appSign);
        WechatAppletUser wechatAppletUser = getOne(query);

        Boolean authorized = wechatLoginDTO.getAuthorized();
        String nickname = wechatLoginDTO.getNickname();
        String avatarUrl = wechatLoginDTO.getAvatarUrl();
        Integer gender = wechatLoginDTO.getGender();
        String phone = wechatLoginDTO.getPhone();
        String location = wechatLoginDTO.getCountry() + wechatLoginDTO.getProvince() + wechatLoginDTO.getCity();
        // 微信小程序用户为空，则在tb_user表也为空
        if (Objects.isNull(wechatAppletUser)) {
            Long userId = userService.addUser(appId, appSign, AppTypeEnum.WECHAT_APPLET);
            wechatAppletUser = new WechatAppletUser();
            wechatAppletUser.setUserId(userId)
                    .setWechatUserId(wechatUserId)
                    .setAppId(appId)
                    .setAppSign(appSign)
                    .setOpenId(openId)
                    .setUnionId(unionId)
                    .setPhone(phone)
                    .setNickname(nickname)
                    .setAvatar(avatarUrl)
                    .setGender(gender)
                    .setLocation(location);
            Assert.isTrue(save(wechatAppletUser), BaseExceptionMessage.WECHATAPPLETUSER_SAVE_EXCEPTION);
        } else {
            if (authorized) {
                LambdaUpdateChainWrapper<WechatAppletUser> update = lambdaUpdate();
                update.set(WechatAppletUser::getLocation, location)
                        .set(WechatAppletUser::getPhone, phone)
                        .set(WechatAppletUser::getNickname, nickname)
                        .set(WechatAppletUser::getAvatar, avatarUrl)
                        .set(WechatAppletUser::getGender, gender);
                update(update);
            }
        }
        return wechatAppletUser.getUserId();
    }
}
