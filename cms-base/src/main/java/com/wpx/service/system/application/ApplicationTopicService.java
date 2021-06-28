package com.wpx.service.system.application;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.common.exception.ExceptionMessage;
import com.wpx.common.util.CollectionUtils;
import com.wpx.model.system.applicationtopic.ApplicationTopic;
import com.wpx.model.system.applicationtopic.ApplicationTopicMapper;
import com.wpx.model.system.applicationtopic.pojo.ApplicationTopicItem;
import com.wpx.model.system.applicationtopic.pojo.cms.ApplicationTopicCmsAddDTO;
import com.wpx.model.system.applicationtopic.pojo.cms.ApplicationTopicCmsQueryDTO;
import com.wpx.model.system.applicationtopic.pojo.cms.ApplicationTopicCmsUpdateDTO;
import com.wpx.model.system.applicationtopic.pojo.cms.ApplicationTopicCmsVO;
import com.wpx.redis.SystemRedisService;
import com.wpx.service.system.application.ApplicationService;
import com.wpx.util.Assert;
import com.wpx.util.BeanUtils;
import com.wpx.util.ConversionBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class ApplicationTopicService extends ServiceImpl<ApplicationTopicMapper, ApplicationTopic> {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private SystemRedisService systemRedisService;

    public ApplicationTopicCmsVO findById(Long applicationTopicId) {
        ApplicationTopic applicationTopic = getById(applicationTopicId);
        Assert.notNull(applicationTopic, ExceptionMessage.APPLICATIONTOPIC_NOT_EXIST);
        return toApplicationTopicCmsVO(applicationTopic);
    }

    private ApplicationTopicCmsVO toApplicationTopicCmsVO(ApplicationTopic applicationTopic) {
        return BeanUtils.copyNonNullProperties(applicationTopic, ApplicationTopicCmsVO.class);
    }

    /**
     * 添加主题消息
     *
     * @param addDTO
     */
    @Transactional
    public ApplicationTopicCmsVO saveApplicationTopic(ApplicationTopicCmsAddDTO addDTO) {
        boolean disabled = applicationService.checkApplication(addDTO.getApplicationId());
        ApplicationTopic applicationTopic = BeanUtils.copyNonNullProperties(addDTO, ApplicationTopic.class);
        LocalDateTime time = LocalDateTime.now();
        applicationTopic.setUpdateTime(time).setCreateTime(time).setDeleted(false);
        Assert.isTrue(save(applicationTopic), ExceptionMessage.APPLICATIONTOPIC_SAVE_ERROR);
        if (!disabled) {
            ApplicationTopicItem item = toApplicationTopicItem(applicationTopic);
            systemRedisService.putApplicationTopicMessageToRedis(item);
        }
        return toApplicationTopicCmsVO(applicationTopic);
    }

    private ApplicationTopicItem toApplicationTopicItem(ApplicationTopic applicationTopic) {
        return BeanUtils.copyNonNullProperties(applicationTopic, ApplicationTopicItem.class);
    }

    /**
     * 删除主题消息
     * 由于未确定主题消息所属的应用，需要先查出所属应用
     *
     * @param applicationTopicIds
     */
    @Transactional
    public void deleteApplicationTopics(Set<Long> applicationTopicIds) {
        if (CollectionUtils.isEmpty(applicationTopicIds)) {
            return;
        }
        // 查出主题消息名
        LambdaQueryWrapper<ApplicationTopic> lambda = new QueryWrapper<ApplicationTopic>().lambda();
        lambda.select(ApplicationTopic::getApplicationTopicId)
                .in(ApplicationTopic::getApplicationTopicId, applicationTopicIds);
        Set<String> topics = CollectionUtils.conversionSet(list(lambda), ApplicationTopic::getTopic);
        int count = baseMapper.deleteBatchIds(applicationTopicIds);
        Assert.isTrue(count == applicationTopicIds.size(), ExceptionMessage.APPLICATIONTOPIC_DELETE_ERROR);
        systemRedisService.deleteApplicationTopicMessageForRedis(topics);
    }

    /**
     * 更新应用主题消息信息
     *
     * @param updateDTO
     */
    @Transactional
    public ApplicationTopicCmsVO updateApplicationTopic(ApplicationTopicCmsUpdateDTO updateDTO) {
        boolean disabled = applicationService.checkApplication(updateDTO.getApplicationId());
        ApplicationTopic applicationTopic = BeanUtils.copyNonNullProperties(updateDTO, ApplicationTopic.class);
        applicationTopic.setUpdateTime(LocalDateTime.now());
        Assert.isTrue(updateById(applicationTopic), ExceptionMessage.APPLICATIONTOPIC_UPDATE_ERROR);
        ApplicationTopic topic = getById(applicationTopic.getApplicationTopicId());
        if (!disabled) {
            ApplicationTopicItem item = toApplicationTopicItem(applicationTopic);
            systemRedisService.putApplicationTopicMessageToRedis(item);
        }
        return toApplicationTopicCmsVO(topic);
    }

    public IPage<ApplicationTopicCmsVO> findApplicationTopicPage(ApplicationTopicCmsQueryDTO applicationTopicQueryDTO, Page page) {
        QueryWrapper<ApplicationTopic> queryWrapper = new QueryWrapper<>();
        return ConversionBeanUtils.conversionBean(page(page, queryWrapper), this::toApplicationTopicCmsVO);
    }

    /**
     * 由于应用信息被删除，应用所属主题消息信息也相应被删除
     *
     * @param applicationIds
     */
    public void deleteByApplicationIds(Set<Long> applicationIds) {
        if (CollectionUtils.nonEmpty(applicationIds)) {
            LambdaQueryWrapper<ApplicationTopic> wrapper = new QueryWrapper<ApplicationTopic>().lambda();
            wrapper.select(ApplicationTopic::getTopic).in(ApplicationTopic::getApplicationId, applicationIds);
            Set<String> topics = CollectionUtils.conversionSet(list(wrapper), ApplicationTopic::getTopic);

            LambdaUpdateWrapper<ApplicationTopic> lambda = new UpdateWrapper<ApplicationTopic>().lambda();
            lambda.set(ApplicationTopic::getDeleted, true).in(ApplicationTopic::getApplicationId, applicationIds);
            update(lambda);

            systemRedisService.deleteApplicationTopicMessageForRedis(topics);
        }

    }

}
