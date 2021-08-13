package com.wpx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.quartzjob.pojo.*;
import com.wpx.service.QuartzJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 不会飞的小鹏
 *  定时任务管理后台
 */
@Api(tags = "定时任务 -- 管理")
@RestController
@RequestMapping("api/scheduler/quartzJob")
public class QuartzJobController {

    @Autowired
    private QuartzJobService quartzJobService;

    @PostMapping
    @ApiOperation(value = "创建任务")
    public ResultVO<QuartzJobVO> createQuartzJob(@RequestBody QuartzJobAddDTO addDTO) {
        return ResultVO.successData(quartzJobService.createJob(addDTO));
    }

    @GetMapping
    @ApiOperation(value = "查看任务")
    public ResultVO<QuartzJobVO> getQuartzJob(@ModelAttribute QuartzJobGetDTO getDTO) {
        return ResultVO.successData(quartzJobService.getJob(getDTO));
    }

    @PutMapping("pause")
    @ApiOperation(value = "暂停任务")
    public ResultVO<QuartzJobVO> pauseTrigger(@RequestBody QuartzJobTriggerDTO triggerDTO) {
        return ResultVO.successData(quartzJobService.pauseTrigger(triggerDTO));
    }

    @PutMapping("reschedule")
    @ApiOperation(value = "恢复任务")
    public ResultVO<QuartzJobVO> rescheduleTrigger(@RequestBody QuartzJobTriggerDTO triggerDTO) {
        return ResultVO.successData(quartzJobService.rescheduleJob(triggerDTO));
    }

    @DeleteMapping
    @ApiOperation(value = "移除任务")
    public ResultVO<BooleanVO> deleteQuartzJob(@ModelAttribute QuartzJobDeleteDTO deleteDTO) {
        quartzJobService.deleteJob(deleteDTO);
        return ResultVO.successData(BooleanVO.result(true));
    }

    @PostMapping("list")
    @ApiOperation(value = "创建多个任务")
    public ResultVO<List<QuartzJobVO>> createQuartzJob(@RequestBody QuartzJobListAddDTO listAddDTO) {
        return ResultVO.successData(quartzJobService.createJobList(listAddDTO));
    }

    @GetMapping("list")
    @ApiOperation(value = "获取任务列表")
    public ResultVO<List<QuartzJobVO>> listQuartzJob() {
        return ResultVO.successData(quartzJobService.listJob());
    }

    @GetMapping("page")
    @ApiOperation(value = "分页获取任务")
    public ResultVO<IPage<QuartzJobVO>> pageQuartzJob(@ModelAttribute QuartzJobQueryDTO queryDTO, Page page) {
        return ResultVO.successData(quartzJobService.pageJob(queryDTO, page));
    }

    @GetMapping("exists")
    @ApiOperation(value = "检查是否有同名任务")
    public ResultVO<BooleanVO> checkExists(@ModelAttribute QuartzJobExistsCheckDTO existsCheckDTO) {
        return ResultVO.successData(quartzJobService.checkExists(existsCheckDTO));
    }

    @GetMapping("group/list")
    @ApiOperation(value = "获取任务分组")
    public ResultVO<List<String>> listJobGroup() {
        return ResultVO.successData(quartzJobService.listJobGroup());
    }

}
