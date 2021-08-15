package com.wpx.service.user;

import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.WechatAppletUserMapper;
import com.wpx.model.user.wechatappletuser.WechatAppletUser;
import com.wpx.model.user.wechatappletuser.pojo.cms.WechatAppletUserCmsQueryDTO;
import com.wpx.model.user.wechatappletuser.pojo.cms.WechatAppletUserCmsVO;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wpx.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Set;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.util.ConversionBeanUtils;
import org.springframework.transaction.annotation.Transactional;
import com.wpx.util.Assert;

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

    public WechatAppletUserCmsVO findById(Long wechatAppletUserId) {
        WechatAppletUser wechatAppletUser = getById(wechatAppletUserId);
        Assert.notNull(wechatAppletUser, BaseExceptionMessage.WECHATAPPLETUSER_NOT_EXIST_EXCEPTION);
        return toWechatAppletUserCmsVO(wechatAppletUser);
    }

    private WechatAppletUserCmsVO toWechatAppletUserCmsVO(WechatAppletUser wechatAppletUser) {
        return BeanUtils.copyNonNullProperties(wechatAppletUser, WechatAppletUserCmsVO.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteWechatAppletUsers(Set<Long> wechatAppletUserIds) {
        int count = baseMapper.deleteBatchIds(wechatAppletUserIds);
        Assert.isTrue(count == wechatAppletUserIds.size(), BaseExceptionMessage.WECHATAPPLETUSER_DELETE_EXCEPTION);
    }

    public IPage<WechatAppletUserCmsVO> findWechatAppletUserPage (WechatAppletUserCmsQueryDTO queryDTO, Page page) {
        QueryWrapper<WechatAppletUser> queryWrapper = new QueryWrapper<>();
        return ConversionBeanUtils.conversionBean(page(page, queryWrapper), this::toWechatAppletUserCmsVO);
    }
}
