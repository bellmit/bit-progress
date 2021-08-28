package com.wpx.service.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.WechatUserMapper;
import com.wpx.model.user.login.WechatLoginDTO;
import com.wpx.model.user.wechatuser.WechatUser;
import com.wpx.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
* <p>
    * 用户信息 服务类
    * </p>
*
* @author 不会飞的小鹏
* created on 2021-08-13
*/
@Service
@Slf4j
public class WechatUserService extends ServiceImpl<WechatUserMapper, WechatUser> {

    /**
     * 更新微信用户
     *
     * @param unionId
     * @param wechatLoginDTO
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public Long updateUser(String unionId, WechatLoginDTO wechatLoginDTO) {
        LambdaQueryChainWrapper<WechatUser> query = lambdaQuery().eq(WechatUser::getUnionId, unionId);
        WechatUser wechatUser = getOne(query);
        String nickname = wechatLoginDTO.getNickname();
        String avatarUrl = wechatLoginDTO.getAvatarUrl();
        Integer gender = wechatLoginDTO.getGender();
        if (Objects.isNull(wechatUser)) {
            wechatUser = new WechatUser();
            wechatUser.setNickname(nickname)
                    .setAvatar(avatarUrl)
                    .setGender(gender);
            Assert.isTrue(save(wechatUser), BaseExceptionMessage.WECHATUSER_SAVE_EXCEPTION);
        } else {
            if (wechatLoginDTO.getAuthorized()) {
                LambdaUpdateChainWrapper<WechatUser> update = lambdaUpdate();
                update.set(WechatUser::getGender, gender)
                        .set(WechatUser::getNickname, nickname)
                        .set(WechatUser::getAvatar, avatarUrl);
                update(update);
            }
        }
        return wechatUser.getWechatUserId();
    }

}
