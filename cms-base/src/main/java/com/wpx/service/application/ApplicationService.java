package com.wpx.service.application;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.exception.ExceptionMessage;
import com.wpx.mapper.application.ApplicationMapper;
import com.wpx.model.application.application.Application;
import com.wpx.model.application.application.pojo.ApplicationMessageVO;
import com.wpx.util.CollectionUtils;
import com.wpx.model.application.application.pojo.cms.*;
import com.wpx.redis.SystemRedisService;
import com.wpx.util.Assert;
import com.wpx.util.BeanUtils;
import com.wpx.util.ConversionBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 应用信息 服务类
 * </p>
 *
 * @author wupengxiao
 * @since 2021-06-07
 */
@Service
@Slf4j
public class ApplicationService extends ServiceImpl<ApplicationMapper, Application> {

    @Autowired
    private ApplicationTopicService applicationTopicService;

    @Autowired
    private SystemRedisService systemRedisService;

    public ApplicationCmsVO findById(Long applicationId) {
        Application application = getById(applicationId);
        Assert.notNull(application, ExceptionMessage.APPLICATION_NOT_EXIST);
        return toApplicationCmsVO(application);
    }

    private ApplicationCmsVO toApplicationCmsVO(Application application) {
        return BeanUtils.copyNonNullProperties(application, ApplicationCmsVO.class);
    }

    /**
     * 新增应用信息
     *
     * @param applicationAddDTO
     */
    @Transactional
    public ApplicationCmsVO saveApplication(ApplicationCmsAddDTO applicationAddDTO) {
        Application application = BeanUtils.copyNonNullProperties(applicationAddDTO, Application.class);
        LocalDateTime time = LocalDateTime.now();
        application.setDisabled(false).setUpdateTime(time).setCreateTime(time).setDeleted(false);
        Assert.isTrue(save(application), ExceptionMessage.APPLICATION_SAVE_ERROR);
        return toApplicationCmsVO(application);
    }

    /**
     * 删除应用信息，先查出应用的标识sign再进行删除
     *
     * @param applicationIds
     */
    @Transactional
    public void deleteApplications(Set<Long> applicationIds) {
        if (CollectionUtils.isEmpty(applicationIds)) {
            return;
        }
        LambdaQueryWrapper<Application> lambda = new QueryWrapper<Application>().lambda();
        lambda.select(Application::getAppSign).in(Application::getApplicationId, applicationIds);
        Set<String> appSigns = CollectionUtils.conversionSet(list(lambda), Application::getAppSign);
        int count = baseMapper.deleteBatchIds(applicationIds);
        Assert.isTrue(count == applicationIds.size(), ExceptionMessage.APPLICATION_DELETE_ERROR);
        // 删除应用的主题消息信息
        applicationTopicService.deleteByApplicationIds(applicationIds);
        // 从缓存中删除应用信息
        systemRedisService.deleteApplicationMessageForRedis(appSigns);
    }

    /**
     * 更新应用信息
     *
     * @param applicationUpdateDTO
     */
    @Transactional
    public ApplicationCmsVO updateApplication(ApplicationCmsUpdateDTO applicationUpdateDTO) {
        Application application = BeanUtils.copyNonNullProperties(applicationUpdateDTO, Application.class);
        application.setUpdateTime(LocalDateTime.now());
        Assert.isTrue(updateById(application), ExceptionMessage.APPLICATION_UPDATE_ERROR);
        Application app = getById(application.getApplicationId());
        return toApplicationCmsVO(app);
    }

    public IPage<ApplicationCmsVO> findApplicationPage(ApplicationCmsQueryDTO applicationQueryDTO, Page page) {
        QueryWrapper<Application> queryWrapper = new QueryWrapper<>();
        return ConversionBeanUtils.conversionBean(page(page, queryWrapper), this::toApplicationCmsVO);
    }

    /**
     * 禁用和启用应用信息
     *
     * @param disabledDTO
     */
    public ApplicationCmsVO disabled(ApplicationCmsDisabledDTO disabledDTO) {
        Long applicationId = disabledDTO.getApplicationId();
        Application application = getById(applicationId);
        Assert.notNull(application, ExceptionMessage.APPLICATION_NOT_EXIST);
        Boolean disabled = application.getDisabled();
        application.setDisabled(!disabled);
        Assert.isTrue(updateById(application), ExceptionMessage.APPLICATION_UPDATE_ERROR);
        return toApplicationCmsVO(application);
    }

    /**
     * 获取应用列表
     */
    public List<ApplicationMessageVO> listApplicationMessage() {
        return CollectionUtils.conversionList(list(), this::toApplicationMessageVO);
    }

    private ApplicationMessageVO toApplicationMessageVO(Application application) {
        return BeanUtils.copyNonNullProperties(application, ApplicationMessageVO.class);
    }

    /**
     * 检查应用是否存在并返回应用的禁用和启用状态
     *
     * @param applicationId
     */
    public boolean checkApplication(Long applicationId) {
        LambdaQueryWrapper<Application> lambda = new QueryWrapper<Application>().lambda();
        lambda.select(Application::getDisabled).eq(Application::getApplicationId, applicationId);
        Application application = getOne(lambda);
        Assert.notNull(application, ExceptionMessage.APPLICATION_NOT_EXIST);
        return application.getDisabled();
    }

}
