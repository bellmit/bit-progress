package com.wpx.service.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.app.AppMapper;
import com.wpx.model.app.app.App;
import com.wpx.model.app.app.pojo.cms.AppCmsAddDTO;
import com.wpx.model.app.app.pojo.cms.AppCmsQueryDTO;
import com.wpx.model.app.app.pojo.cms.AppCmsUpdateDTO;
import com.wpx.model.app.app.pojo.cms.AppCmsVO;
import com.wpx.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Set;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.util.ConversionBeanUtils;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import com.wpx.util.Assert;

/**
* <p>
    * 应用信息 服务类
    * </p>
*
* @author 不会飞的小鹏
* create on 2021-08-14
*/
@Service
@Slf4j
public class AppService extends ServiceImpl<AppMapper, App> {

    public AppCmsVO findById(Long appId) {
        App app = getById(appId);
        Assert.notNull(app, BaseExceptionMessage.APP_NOT_EXIST_EXCEPTION);
        return toAppCmsVO(app);
    }

    private AppCmsVO toAppCmsVO(App app) {
        return BeanUtils.copyNonNullProperties(app, AppCmsVO.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public AppCmsVO saveApp(AppCmsAddDTO appAddDTO) {
        App app= BeanUtils.copyNonNullProperties(appAddDTO, App.class);
        LocalDateTime time=LocalDateTime.now();
        app.setUpdateTime(time).setCreateTime(time).setDeleted(false);
        Assert.isTrue(save(app), BaseExceptionMessage.APP_SAVE_EXCEPTION);
        log.info("保存数据---:{}", app);
        return toAppCmsVO(app);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteApps(Set<Long> appIds) {
        int count = baseMapper.deleteBatchIds(appIds);
        Assert.isTrue(count == appIds.size(), BaseExceptionMessage.APP_DELETE_EXCEPTION);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public AppCmsVO updateApp(AppCmsUpdateDTO appUpdateDTO) {
        App app = BeanUtils.copyNonNullProperties(appUpdateDTO, App.class);
        app.setUpdateTime(LocalDateTime.now());
        Assert.isTrue(updateById(app), BaseExceptionMessage.APP_UPDATE_EXCEPTION);
        return findById(app.getAppId());
    }

    public IPage<AppCmsVO> findAppPage (AppCmsQueryDTO appQueryDTO, Page page) {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        return ConversionBeanUtils.conversionBean(page(page, queryWrapper), this::toAppCmsVO);
    }
}
