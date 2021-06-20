package com.wpx.service.system.application;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.common.exception.MessageCodes;
import com.wpx.common.util.CollectionUtils;
import com.wpx.model.system.application.Application;
import com.wpx.model.system.application.ApplicationMapper;
import com.wpx.model.system.application.pojo.ApplicationItem;
import com.wpx.model.system.application.pojo.ApplicationMessageVO;
import com.wpx.model.system.application.pojo.cms.*;
import com.wpx.redis.SystemRedisService;
import com.wpx.service.system.applicationtopic.ApplicationTopicService;
import com.wpx.util.BeanUtils;
import com.wpx.util.ConversionBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.HashSet;
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

    public ApplicationCmsVO findById(Integer applicationId) {
        Application application = getById(applicationId);
        Assert.notNull(application, MessageCodes.APPLICATION_NOT_EXIST);
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
        Assert.isTrue(save(application), MessageCodes.APPLICATION_SAVE_ERROR);
        // 将应用信息put到缓存中
        ApplicationItem item = toApplicationItem(application);
        systemRedisService.putApplicationMessageToRedis(item);
        return toApplicationCmsVO(application);
    }

    private ApplicationItem toApplicationItem(Application application) {
        return BeanUtils.copyNonNullProperties(application, ApplicationItem.class);
    }

    /**
     * 删除应用信息，先查出应用的标识sign再进行删除
     *
     * @param applicationIds
     */
    @Transactional
    public void deleteApplications(Set<Integer> applicationIds) {
        if (CollectionUtils.isEmpty(applicationIds)) {
            return;
        }
        LambdaQueryWrapper<Application> lambda = new QueryWrapper<Application>().lambda();
        lambda.select(Application::getAppSign).in(Application::getApplicationId, applicationIds);
        Set<String> appSigns = CollectionUtils.conversionSet(list(lambda), Application::getAppSign);
        int count = baseMapper.deleteBatchIds(applicationIds);
        Assert.isTrue(count == applicationIds.size(), MessageCodes.APPLICATION_DELETE_ERROR);
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
        Assert.isTrue(updateById(application), MessageCodes.APPLICATION_UPDATE_ERROR);
        Application app = getById(application.getApplicationId());
        // 如果应用没有被禁用，就更新redis中的应用信息
        if (!app.getDisabled()) {
            ApplicationItem item = toApplicationItem(app);
            systemRedisService.putApplicationMessageToRedis(item);
        }
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
        Integer applicationId = disabledDTO.getApplicationId();
        Application application = getById(applicationId);
        Assert.notNull(application, MessageCodes.APPLICATION_NOT_EXIST);
        Boolean disabled = application.getDisabled();
        application.setDisabled(!disabled);
        Assert.isTrue(updateById(application), MessageCodes.APPLICATION_UPDATE_ERROR);
        // 如果应用原本是未被禁用，则要将应用信息从redis中删除；如果原本被禁用，则要将应用信息缓存到redis
        if (disabled) {
            ApplicationItem item = toApplicationItem(application);
            systemRedisService.putApplicationMessageToRedis(item);
        } else {
            Set<String> set = new HashSet<>();
            set.add(application.getAppSign());
            systemRedisService.deleteApplicationMessageForRedis(set);
        }
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
    public boolean checkApplication(Integer applicationId) {
        LambdaQueryWrapper<Application> lambda = new QueryWrapper<Application>().lambda();
        lambda.select(Application::getDisabled).eq(Application::getApplicationId, applicationId);
        Application application = getOne(lambda);
        Assert.notNull(application, MessageCodes.APPLICATION_NOT_EXIST);
        return application.getDisabled();
    }

    /**
     * 根据应用ID获取AppSign
     *
     * @param applicationIds
     */
    public List<Application> listApplicationSign(Set<Integer> applicationIds) {
        LambdaQueryWrapper<Application> lambda = new QueryWrapper<Application>().lambda();
        lambda.select(Application::getApplicationId, Application::getAppSign)
                .in(Application::getApplicationId, applicationIds);
        return list(lambda);
    }
}
