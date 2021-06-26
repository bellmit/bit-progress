package com.wpx.service.system.applet;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.common.exception.MessageCodes;
import com.wpx.model.system.applet.Applet;
import com.wpx.model.system.applet.AppletMapper;
import com.wpx.model.system.applet.pojo.cms.AppletCmsAddDTO;
import com.wpx.model.system.applet.pojo.cms.AppletCmsQueryDTO;
import com.wpx.model.system.applet.pojo.cms.AppletCmsUpdateDTO;
import com.wpx.model.system.applet.pojo.cms.AppletCmsVO;
import com.wpx.util.BeanUtils;
import com.wpx.util.ConversionBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 小程序信息 服务类
 * </p>
 *
 * @author wupengxiao
 * @since 2021-06-26
 */
@Service
@Slf4j
public class AppletService extends ServiceImpl<AppletMapper, Applet> {

    public AppletCmsVO findById(Long appletId) {
        Applet applet = getById(appletId);
        Assert.notNull(applet, MessageCodes.APPLET_NOT_EXIST);
        return toAppletCmsVO(applet);
    }

    private AppletCmsVO toAppletCmsVO(Applet applet) {
        return BeanUtils.copyNonNullProperties(applet, AppletCmsVO.class);
    }

    @Transactional
    public AppletCmsVO saveApplet(AppletCmsAddDTO appletAddDTO) {
        Applet applet = BeanUtils.copyNonNullProperties(appletAddDTO, Applet.class);
        LocalDateTime time = LocalDateTime.now();
        applet.setUpdateTime(time).setCreateTime(time).setDeleted(false);
        boolean isSuccess = save(applet);
        Assert.isTrue(isSuccess, MessageCodes.APPLET_SAVE_ERROR);
        log.info("保存数据---:{}", applet);
        return toAppletCmsVO(applet);
    }

    @Transactional
    public void deleteApplets(Set<Integer> appletIds) {
        int count = baseMapper.deleteBatchIds(appletIds);
        Assert.isTrue(count == appletIds.size(), MessageCodes.APPLET_DELETE_ERROR);
        log.info("删除数据:ids{}", appletIds);
    }

    @Transactional
    public AppletCmsVO updateApplet(AppletCmsUpdateDTO appletUpdateDTO) {
        Applet applet = BeanUtils.copyNonNullProperties(appletUpdateDTO, Applet.class);
        applet.setUpdateTime(LocalDateTime.now());

        Assert.isTrue(updateById(applet), MessageCodes.APPLET_UPDATE_ERROR);
        log.info("修改数据：bean:{}", appletUpdateDTO);
        return findById(applet.getAppletId());
    }

    public IPage<AppletCmsVO> findAppletPage(AppletCmsQueryDTO appletQueryDTO, Page page) {
        QueryWrapper<Applet> queryWrapper = new QueryWrapper<>();
        //queryWrapper.lambda().eq(Applet::getAppletId, appletQueryDTO.getAppletId);
        return ConversionBeanUtils.conversionBean(page(page, queryWrapper), this::toAppletCmsVO);
    }

}
