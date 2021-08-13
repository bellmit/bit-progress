package com.wpx.scheduler.quartzjob;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.quartzjob.pojo.QuartzJobAddDTO;
import com.wpx.model.quartzjob.pojo.QuartzJobVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author 不会飞的小鹏
 * @Description: quartzJob feign 服务调用类
 */
@FeignClient(name = "scheduler", path = "rest/scheduler/quartzJob", fallbackFactory = QuartzJobFeignServiceFallback.class)
public interface QuartzJobFeignService {

    /**
     * 创建定时任务
     *
     * @param addDTO  需要添加的定时任务信息
     * @return: ResultVO<QuartzJobVO>
     */
    @PostMapping
    ResultVO<QuartzJobVO> createQuartzJob(@RequestBody QuartzJobAddDTO addDTO);

    /**
     * 移除定时任务
     *
     * @param jobKeyName  需要移除的定时任务名称
     * @param jobKeyGroup  需要移除的定时任务分组
     * @param triggerKeyName  需要移除的定时任务触发器名称
     * @param triggerKeyGroup  需要移除的定时任务触发器分组
     * @return: ResultVO<BooleanVO>
     */
    @DeleteMapping
    ResultVO<BooleanVO> deleteQuartzJob(@RequestParam String jobKeyName,
                                        @RequestParam String jobKeyGroup,
                                        @RequestParam String triggerKeyName,
                                        @RequestParam String triggerKeyGroup);

    /**
     * 查询定时任务是否存在同名任务
     *
     * @param jobName 需要检查的任务名称
     * @param jobGroupName  需要检查的任务分组
     * @return: ResultVO<BooleanVO>
     */
    @GetMapping("exists")
    ResultVO<BooleanVO> checkExists(@RequestParam String jobName,
                                    @RequestParam String jobGroupName);

}
