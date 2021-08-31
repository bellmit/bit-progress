package com.wpx.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.WechatUserMapper;
import com.wpx.model.login.WechatLoginDTO;
import com.wpx.model.user.wechatuser.WechatUser;
import com.wpx.model.user.wechatuser.pojo.web.WechatUserWebVO;
import com.wpx.util.Assert;
import com.wpx.util.BeanUtils;
import com.wpx.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    public WechatUserWebVO findById(Long wechatUserId) {
        WechatUser wechatUser = getWechatUserById(wechatUserId);
        Assert.notNull(wechatUser, BaseExceptionMessage.WECHATUSER_NOT_EXIST_EXCEPTION);
        return toWechatUserWebVO(wechatUser);
    }

    public WechatUser getWechatUserById(Long wechatUserId) {
        return getById(wechatUserId);
    }

    public List<WechatUser> listWechatUser(Set<Long> wechatUserIds) {
        if (CollectionUtils.isEmpty(wechatUserIds)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<WechatUser> lambda = new QueryWrapper<WechatUser>().lambda();
        lambda.in(WechatUser::getWechatUserId, wechatUserIds);
        return list(lambda);
    }

    private WechatUserWebVO toWechatUserWebVO(WechatUser wechatUser) {
        return BeanUtils.copyNonNullProperties(wechatUser, WechatUserWebVO.class);
    }

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
