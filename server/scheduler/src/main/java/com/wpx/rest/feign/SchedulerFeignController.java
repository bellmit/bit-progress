package com.wpx.rest.feign;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.quartzjob.pojo.QuartzJobAddDTO;
import com.wpx.model.quartzjob.pojo.QuartzJobDeleteDTO;
import com.wpx.model.quartzjob.pojo.QuartzJobExistsCheckDTO;
import com.wpx.model.quartzjob.pojo.QuartzJobVO;
import com.wpx.service.QuartzJobService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wpx
 * Created on 2021/2/6 14:17
 * @description：
 */
@RestController
@RequestMapping("rest/scheduler/quartzJob")
public class SchedulerFeignController {

    @Autowired
    private QuartzJobService quartzJobService;

    /**
     * 创建定时任务
     *
     * @param addDTO  需要添加的定时任务信息
     */
    @PostMapping
    @ApiOperation(value = "创建任务")
    public ResultVO<QuartzJobVO> createQuartzJob(@RequestBody QuartzJobAddDTO addDTO) {
        return ResultVO.successData(quartzJobService.createJob(addDTO));
    }

    /**
     * 移除定时任务
     *
     * @param deleteDTO  需要移除的定时任务信息
     */
    @DeleteMapping
    public ResultVO<BooleanVO> deleteQuartzJob(@ModelAttribute QuartzJobDeleteDTO deleteDTO) {
        quartzJobService.deleteJob(deleteDTO);
        return ResultVO.successData(BooleanVO.result(true));
    }

    /**
     * 查询定时任务是否存在同名任务
     *
     * @param existsCheckDTO  需要检查的任务信息
     */
    @GetMapping("exists")
    public ResultVO<BooleanVO> checkExists(@ModelAttribute QuartzJobExistsCheckDTO existsCheckDTO) {
        return ResultVO.successData(quartzJobService.checkExists(existsCheckDTO));
    }

}
