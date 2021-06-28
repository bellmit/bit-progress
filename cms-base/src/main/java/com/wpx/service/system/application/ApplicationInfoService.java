package com.wpx.service.system.application;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.model.system.applicationinfo.ApplicationInfo;
import com.wpx.model.system.applicationinfo.ApplicationInfoMapper;
import com.wpx.model.system.applicationinfo.pojo.cms.ApplicationInfoCmsAddDTO;
import com.wpx.model.system.applicationinfo.pojo.cms.ApplicationInfoCmsQueryDTO;
import com.wpx.model.system.applicationinfo.pojo.cms.ApplicationInfoCmsUpdateDTO;
import com.wpx.model.system.applicationinfo.pojo.cms.ApplicationInfoCmsVO;
import com.wpx.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.wpx.common.exception.ExceptionMessage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Set;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.util.ConversionBeanUtils;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import com.wpx.util.Assert;
/**
* <p>
    * 应用配置信息 服务类
    * </p>
*
* @author wupengxiao
* @since 2021-06-27
*/
@Service
@Slf4j
public class ApplicationInfoService extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> {

    public ApplicationInfoCmsVO findById(Long applicationInfoId) {
        ApplicationInfo applicationInfo = getById(applicationInfoId);
        Assert.notNull(applicationInfo, ExceptionMessage.APPLICATIONINFO_NOT_EXIST);
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
    @Transactional
    public ApplicationInfoCmsVO saveApplicationInfo(ApplicationInfoCmsAddDTO addDTO) {
        ApplicationInfo applicationInfo = BeanUtils.copyNonNullProperties(addDTO, ApplicationInfo.class);
        LocalDateTime time=LocalDateTime.now();
        applicationInfo.setUpdateTime(time).setCreateTime(time).setDeleted(false);
        Assert.isTrue(save(applicationInfo), ExceptionMessage.APPLICATIONINFO_SAVE_ERROR);
        return toApplicationInfoCmsVO(applicationInfo);
    }

    @Transactional
    public void deleteApplicationInfos(Set<Long> applicationInfoIds) {
        int count = baseMapper.deleteBatchIds(applicationInfoIds);
        Assert.isTrue(count == applicationInfoIds.size(), ExceptionMessage.APPLICATIONINFO_DELETE_ERROR);
        log.info("删除数据:ids{}", applicationInfoIds);
    }

    @Transactional
    public ApplicationInfoCmsVO updateApplicationInfo(ApplicationInfoCmsUpdateDTO applicationInfoUpdateDTO) {
        ApplicationInfo applicationInfo = BeanUtils.copyNonNullProperties(applicationInfoUpdateDTO, ApplicationInfo.class);
        applicationInfo.setUpdateTime(LocalDateTime.now());
        Assert.isTrue(updateById(applicationInfo), ExceptionMessage.APPLICATIONINFO_UPDATE_ERROR);
        log.info("修改数据：bean:{}", applicationInfoUpdateDTO);
        return findById(applicationInfo.getApplicationInfoId());
    }

    public IPage<ApplicationInfoCmsVO> findApplicationInfoPage (ApplicationInfoCmsQueryDTO applicationInfoQueryDTO, Page page) {
        QueryWrapper<ApplicationInfo> queryWrapper = new QueryWrapper<>();
        //queryWrapper.lambda().eq(ApplicationInfo::getApplicationInfoId, applicationInfoQueryDTO.getApplicationInfoId);
        return ConversionBeanUtils.conversionBean(page(page, queryWrapper), this::toApplicationInfoCmsVO);
    }
}
