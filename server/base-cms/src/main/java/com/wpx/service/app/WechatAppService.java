package com.wpx.service.app;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.app.WechatAppMapper;
import com.wpx.model.app.wechatapp.WechatApp;
import com.wpx.model.app.wechatapp.pojo.cms.WechatAppCmsAddDTO;
import com.wpx.model.app.wechatapp.pojo.cms.WechatAppCmsQueryDTO;
import com.wpx.model.app.wechatapp.pojo.cms.WechatAppCmsUpdateDTO;
import com.wpx.model.app.wechatapp.pojo.cms.WechatAppCmsVO;
import com.wpx.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Set;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.util.PageUtils;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import com.wpx.util.Assert;

/**
* <p>
    * 微信应用信息 服务类
    * </p>
*
* @author 不会飞的小鹏
*/
@Service
@Slf4j
public class WechatAppService extends ServiceImpl<WechatAppMapper, WechatApp> {

    public WechatAppCmsVO findById(Long wechatAppId) {
        WechatApp wechatApp = getById(wechatAppId);
        Assert.notNull(wechatApp, BaseExceptionMessage.WECHATAPP_NOT_EXIST_EXCEPTION);
        return toWechatAppCmsVO(wechatApp);
    }

    private WechatAppCmsVO toWechatAppCmsVO(WechatApp wechatApp) {
        return BeanUtils.copyNonNullProperties(wechatApp, WechatAppCmsVO.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public WechatAppCmsVO saveWechatApp(WechatAppCmsAddDTO wechatAppAddDTO) {
        WechatApp wechatApp=BeanUtils.copyNonNullProperties(wechatAppAddDTO, WechatApp.class);
        LocalDateTime time=LocalDateTime.now();
        wechatApp.setUpdateTime(time).setCreateTime(time).setDeleted(false);
        Assert.isTrue(save(wechatApp), BaseExceptionMessage.WECHATAPP_SAVE_EXCEPTION);
        return toWechatAppCmsVO(wechatApp);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteWechatApps(Set<Long> wechatAppIds) {
        int count = baseMapper.deleteBatchIds(wechatAppIds);
        Assert.isTrue(count == wechatAppIds.size(), BaseExceptionMessage.WECHATAPP_DELETE_EXCEPTION);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public WechatAppCmsVO updateWechatApp(WechatAppCmsUpdateDTO wechatAppUpdateDTO) {
        WechatApp wechatApp = BeanUtils.copyNonNullProperties(wechatAppUpdateDTO, WechatApp.class);
        wechatApp.setUpdateTime(LocalDateTime.now());
        Assert.isTrue(updateById(wechatApp), BaseExceptionMessage.WECHATAPP_UPDATE_EXCEPTION);
        return findById(wechatApp.getWechatAppId());
    }

    public IPage<WechatAppCmsVO> findWechatAppPage (WechatAppCmsQueryDTO wechatAppQueryDTO, Page page) {
        QueryWrapper<WechatApp> queryWrapper = new QueryWrapper<>();
        return PageUtils.conversionBean(page(page, queryWrapper), this::toWechatAppCmsVO);
    }
}
