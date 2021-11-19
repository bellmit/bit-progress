package com.wpx.scheduler.quartzjob;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 不会飞的小鹏
 * 定时任务服务降级
 */
@Component
public class QuartzJobFeignServiceFallback implements FallbackFactory<QuartzJobFeignService> {

    /**
     * Returns an instance of the fallback appropriate for the given cause.
     *
     * @param cause cause of an exception.
     * @return fallback
     */
    @Override
    public QuartzJobFeignService create(Throwable cause) {
        return null;
    }

}
