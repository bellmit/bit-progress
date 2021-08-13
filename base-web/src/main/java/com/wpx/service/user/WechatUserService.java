package com.wpx.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.WechatUserMapper;
import com.wpx.model.user.wechatuser.WechatUser;
import com.wpx.model.user.wechatuser.pojo.web.WechatUserWebVO;
import com.wpx.util.BeanUtils;
import com.wpx.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import com.wpx.util.Assert;

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

    @Transactional(rollbackFor = RuntimeException.class)
    public WechatUserWebVO saveWechatUser(WechatUser wechatUser) {
        LocalDateTime time=LocalDateTime.now();
        wechatUser.setUpdateTime(time).setCreateTime(time).setDeleted(false);
        Assert.isTrue(save(wechatUser), BaseExceptionMessage.WECHATUSER_SAVE_EXCEPTION);
        return toWechatUserWebVO(wechatUser);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteWechatUsers(Set<Long> wechatUserIds) {
        int count = baseMapper.deleteBatchIds(wechatUserIds);
        Assert.isTrue(count == wechatUserIds.size(), BaseExceptionMessage.WECHATUSER_DELETE_EXCEPTION);
    }

}
