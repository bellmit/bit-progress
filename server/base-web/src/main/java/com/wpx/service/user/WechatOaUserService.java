package com.wpx.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.WechatOaUserMapper;
import com.wpx.model.DecryptResult;
import com.wpx.model.app.app.envm.AppTypeEnum;
import com.wpx.model.login.WechatLoginDTO;
import com.wpx.model.user.wechatoauser.WechatOaUser;
import com.wpx.service.WechatLoginService;
import com.wpx.util.Assert;
import com.wpx.util.CollectionUtils;
import com.wpx.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
* <p>
    * 微信公众号用户信息 服务类
    * </p>
*
* @author 不会飞的小鹏
* create on 2021-08-14
*/
@Service
@Slf4j
public class WechatOaUserService extends ServiceImpl<WechatOaUserMapper, WechatOaUser> {

    @Autowired
    private UserService userService;

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

    /**
     * 更新微信公众号用户
     * 
     * @param wechatLoginDTO
     * @param openId
     * @param unionId
     * @param wechatUserId
     * @param appSign
     * @param appId
     * @return userId
     */
    public Long updateUser(WechatLoginDTO wechatLoginDTO, String openId, String unionId, Long wechatUserId,
                           String appSign, Long appId, String sessionKey) {

        LambdaQueryChainWrapper<WechatOaUser> query = lambdaQuery().eq(WechatOaUser::getOpenId, openId)
                .eq(WechatOaUser::getUnionId, unionId)
                .eq(WechatOaUser::getAppId, appId)
                .eq(WechatOaUser::getAppSign, appSign);
        WechatOaUser wechatOaUser = getOne(query);

        String nickname = wechatLoginDTO.getNickname();
        String avatarUrl = wechatLoginDTO.getAvatarUrl();
        Integer gender = wechatLoginDTO.getGender();
        String location = wechatLoginDTO.getCountry() + wechatLoginDTO.getProvince() + wechatLoginDTO.getCity();
        Boolean authorized = wechatLoginDTO.getAuthorized();
        // 微信公众号用户为空，则在tb_user表也为空
        if (Objects.isNull(wechatOaUser)) {
            Long userId = userService.addUser(appId, appSign, AppTypeEnum.WECHAT_OA);
            wechatOaUser = new WechatOaUser();
            wechatOaUser.setUserId(userId)
                    .setWechatUserId(wechatUserId)
                    .setAppId(appId)
                    .setAppSign(appSign)
                    .setOpenId(openId)
                    .setUnionId(unionId)
                    .setNickname(nickname)
                    .setAvatar(avatarUrl)
                    .setGender(gender)
                    .setLocation(location);
            Assert.isTrue(save(wechatOaUser), BaseExceptionMessage.WECHATOAUSER_SAVE_EXCEPTION);
        } else {
            LambdaUpdateChainWrapper<WechatOaUser> update = lambdaUpdate();
            update.set(WechatOaUser::getSessionKey, sessionKey);
            if (authorized) {
                update.set(WechatOaUser::getLocation, location)
                        .set(WechatOaUser::getNickname, nickname)
                        .set(WechatOaUser::getAvatar, avatarUrl)
                        .set(WechatOaUser::getGender, gender);
            }
            update(update);
        }
        return wechatOaUser.getUserId();
    }

    /**
     * 更新用户的手机号码
     *
     * @param encryptedData
     * @param iv
     * @param userId
     */
    public void updatePhone(String encryptedData, String iv, Long userId) {
        LambdaQueryChainWrapper<WechatOaUser> query = lambdaQuery().select(WechatOaUser::getSessionKey)
                .eq(WechatOaUser::getUserId, userId);
        WechatOaUser wechatOaUser = getOne(query);
        Assert.notNull(wechatOaUser, BaseExceptionMessage.WECHATAPPLETUSER_NOT_EXIST_EXCEPTION);
        String sessionKey = wechatOaUser.getSessionKey();
        DecryptResult decryptResult = WechatLoginService.encryptedWechatData(encryptedData, iv, sessionKey);
        if (Objects.nonNull(decryptResult) && StringUtils.nonEmpty(decryptResult.getPhoneNumber())) {
            LambdaUpdateChainWrapper<WechatOaUser> update = lambdaUpdate();
            update.set(WechatOaUser::getPhone, decryptResult.getPhoneNumber())
                    .eq(WechatOaUser::getUserId, userId);
            update(update);
        }
    }

}
