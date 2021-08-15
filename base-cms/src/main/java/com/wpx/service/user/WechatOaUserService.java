package com.wpx.service.user;

import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.WechatOaUserMapper;
import com.wpx.model.user.wechatoauser.WechatOaUser;
import com.wpx.model.user.wechatoauser.pojo.cms.WechatOaUserCmsQueryDTO;
import com.wpx.model.user.wechatoauser.pojo.cms.WechatOaUserCmsVO;

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
    * 微信公众号用户信息 服务类
    * </p>
*
* @author 不会飞的小鹏
*/
@Service
@Slf4j
public class WechatOaUserService extends ServiceImpl<WechatOaUserMapper, WechatOaUser> {

    public WechatOaUserCmsVO findById(Long wechatOaUserId) {
        WechatOaUser wechatOaUser = getById(wechatOaUserId);
        Assert.notNull(wechatOaUser, BaseExceptionMessage.WECHATOAUSER_NOT_EXIST_EXCEPTION);
        return toWechatOaUserCmsVO(wechatOaUser);
    }

    private WechatOaUserCmsVO toWechatOaUserCmsVO(WechatOaUser wechatOaUser) {
        return BeanUtils.copyNonNullProperties(wechatOaUser, WechatOaUserCmsVO.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteWechatOaUsers(Set<Long> wechatOaUserIds) {
        int count = baseMapper.deleteBatchIds(wechatOaUserIds);
        Assert.isTrue(count == wechatOaUserIds.size(), BaseExceptionMessage.WECHATOAUSER_DELETE_EXCEPTION);
        log.info("删除数据:ids{}", wechatOaUserIds);
    }

    public IPage<WechatOaUserCmsVO> findWechatOaUserPage (WechatOaUserCmsQueryDTO queryDTO, Page page) {
        QueryWrapper<WechatOaUser> queryWrapper = new QueryWrapper<>();
        return ConversionBeanUtils.conversionBean(page(page, queryWrapper), this::toWechatOaUserCmsVO);
    }
}
