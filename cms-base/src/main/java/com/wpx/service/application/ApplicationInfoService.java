package com.wpx.service.application;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.mapper.application.ApplicationInfoMapper;
import com.wpx.model.application.applicationinfo.ApplicationInfo;
import com.wpx.model.application.applicationinfo.pojo.cms.ApplicationInfoCmsAddDTO;
import com.wpx.model.application.applicationinfo.pojo.cms.ApplicationInfoCmsQueryDTO;
import com.wpx.model.application.applicationinfo.pojo.cms.ApplicationInfoCmsUpdateDTO;
import com.wpx.model.application.applicationinfo.pojo.cms.ApplicationInfoCmsVO;
import com.wpx.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.wpx.exception.BaseExceptionMessage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Set;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.util.ConversionBeanUtils;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import com.wpx.util.Assert;
/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 应用配置
 */
@Service
@Slf4j
public class ApplicationInfoService extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> {

    /**
     * 查询应用配置
     *
     * @param applicationInfoId
     */
    public ApplicationInfoCmsVO findById(Long applicationInfoId) {
        ApplicationInfo applicationInfo = getById(applicationInfoId);
        Assert.notNull(applicationInfo, BaseExceptionMessage.APPLICATIONINFO_NOT_EXIST_EXCEPTION);
        return toApplicationInfoCmsVO(applicationInfo);
    }

    private ApplicationInfoCmsVO toApplicationInfoCmsVO(ApplicationInfo applicationInfo) {
        return BeanUtils.copyNonNullProperties(applicationInfo, ApplicationInfoCmsVO.class);
    }

    /**
     * 保存应用配置
     *
     * @param addDTO
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public ApplicationInfoCmsVO saveApplicationInfo(ApplicationInfoCmsAddDTO addDTO) {
        ApplicationInfo applicationInfo = BeanUtils.copyNonNullProperties(addDTO, ApplicationInfo.class);
        LocalDateTime time=LocalDateTime.now();
        applicationInfo.setUpdateTime(time).setCreateTime(time).setDeleted(false);
        Assert.isTrue(save(applicationInfo), BaseExceptionMessage.APPLICATIONINFO_SAVE_EXCEPTION);
        return toApplicationInfoCmsVO(applicationInfo);
    }

    /**
     * 删除应用配置
     *
     * @param applicationInfoIds
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteApplicationInfos(Set<Long> applicationInfoIds) {
        int count = baseMapper.deleteBatchIds(applicationInfoIds);
        Assert.isTrue(count == applicationInfoIds.size(), BaseExceptionMessage.APPLICATIONINFO_DELETE_EXCEPTION);
    }

    /**
     * 更新应用配置
     *
     * @param updateDTO
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public ApplicationInfoCmsVO updateApplicationInfo(ApplicationInfoCmsUpdateDTO updateDTO) {
        ApplicationInfo applicationInfo = BeanUtils.copyNonNullProperties(updateDTO, ApplicationInfo.class);
        applicationInfo.setUpdateTime(LocalDateTime.now());
        Assert.isTrue(updateById(applicationInfo), BaseExceptionMessage.APPLICATIONINFO_UPDATE_EXCEPTION);
        log.info("修改数据：bean:{}", updateDTO);
        return findById(applicationInfo.getApplicationInfoId());
    }

    /**
     * 分页查询应用配置
     *
     * @param queryDTO
     * @param page
     */
    public IPage<ApplicationInfoCmsVO> findApplicationInfoPage (ApplicationInfoCmsQueryDTO queryDTO, Page page) {
        QueryWrapper<ApplicationInfo> queryWrapper = new QueryWrapper<>();
        return ConversionBeanUtils.conversionBean(page(page, queryWrapper), this::toApplicationInfoCmsVO);
    }

}
