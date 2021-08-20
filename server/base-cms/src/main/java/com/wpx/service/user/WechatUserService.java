package com.wpx.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.WechatUserMapper;
import com.wpx.model.user.wechatuser.WechatUser;
import com.wpx.model.user.wechatuser.pojo.cms.WechatUserCmsAddDTO;
import com.wpx.model.user.wechatuser.pojo.cms.WechatUserCmsQueryDTO;
import com.wpx.model.user.wechatuser.pojo.cms.WechatUserCmsUpdateDTO;
import com.wpx.model.user.wechatuser.pojo.cms.WechatUserCmsVO;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wpx.util.BeanUtils;
import com.wpx.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.util.ConversionBeanUtils;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import com.wpx.util.Assert;
/**
* <p>
    * 用户信息 服务类
    * </p>
*
* @author 不会飞的小鹏
*/
@Service
@Slf4j
public class WechatUserService extends ServiceImpl<WechatUserMapper, WechatUser> {

    public WechatUserCmsVO findById(Long wechatUserId) {
        WechatUser wechatUser = getWechatUserById(wechatUserId);
        Assert.notNull(wechatUser, BaseExceptionMessage.WECHATUSER_NOT_EXIST_EXCEPTION);
        return toWechatUserCmsVO(wechatUser);
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

    private WechatUserCmsVO toWechatUserCmsVO(WechatUser wechatUser) {
        return BeanUtils.copyNonNullProperties(wechatUser, WechatUserCmsVO.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public WechatUserCmsVO saveWechatUser(WechatUserCmsAddDTO wechatUserAddDTO) {
        WechatUser wechatUser = BeanUtils.copyNonNullProperties(wechatUserAddDTO, WechatUser.class);
        LocalDateTime time=LocalDateTime.now();
        wechatUser.setUpdateTime(time).setCreateTime(time).setDeleted(false);
        Assert.isTrue(save(wechatUser), BaseExceptionMessage.WECHATUSER_SAVE_EXCEPTION);
        log.info("保存数据---:{}", wechatUser);
        return toWechatUserCmsVO(wechatUser);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteWechatUsers(Set<Long> wechatUserIds) {
        int count = baseMapper.deleteBatchIds(wechatUserIds);
        Assert.isTrue(count == wechatUserIds.size(), BaseExceptionMessage.WECHATUSER_DELETE_EXCEPTION);
        log.info("删除数据:ids{}", wechatUserIds);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public WechatUserCmsVO updateWechatUser(WechatUserCmsUpdateDTO wechatUserUpdateDTO) {
        WechatUser wechatUser = BeanUtils.copyNonNullProperties(wechatUserUpdateDTO, WechatUser.class);
        wechatUser.setUpdateTime(LocalDateTime.now());
        Assert.isTrue(updateById(wechatUser), BaseExceptionMessage.WECHATUSER_UPDATE_EXCEPTION);
        log.info("修改数据：bean:{}", wechatUserUpdateDTO);
        return findById(wechatUser.getWechatUserId());
    }

    public IPage<WechatUserCmsVO> findWechatUserPage (WechatUserCmsQueryDTO wechatUserQueryDTO, Page page) {
        QueryWrapper<WechatUser> queryWrapper = new QueryWrapper<>();
        return ConversionBeanUtils.conversionBean(page(page, queryWrapper), this::toWechatUserCmsVO);
    }
}
