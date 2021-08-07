package com.wpx.controller.system.application;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.model.ResultVO;
import com.wpx.model.application.applicationtopic.pojo.cms.ApplicationTopicCmsAddDTO;
import com.wpx.model.application.applicationtopic.pojo.cms.ApplicationTopicCmsQueryDTO;
import com.wpx.model.application.applicationtopic.pojo.cms.ApplicationTopicCmsUpdateDTO;
import com.wpx.model.application.applicationtopic.pojo.cms.ApplicationTopicCmsVO;
import com.wpx.service.application.ApplicationTopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @Author: 不会飞的小鹏 
 * @since 2021-06-07
 */
@Api(tags = {"系统 -- 应用主题消息"})
@RestController
@RequestMapping("/api/base/system/applicationTopic")
public class ApplicationTopicController {

    @Autowired
    private ApplicationTopicService applicationTopicService;

    @GetMapping
    @ApiOperation("查询详情")
    public ResultVO<ApplicationTopicCmsVO> findById(@RequestParam @ApiParam("applicationTopicId") Long applicationTopicId) {
        return ResultVO.successData(applicationTopicService.findById(applicationTopicId));
    }

    @PostMapping
    @ApiOperation("添加")
    public ResultVO<ApplicationTopicCmsVO> save(@RequestBody @Valid ApplicationTopicCmsAddDTO applicationTopicAddDTO) {
        return ResultVO.successData(applicationTopicService.saveApplicationTopic(applicationTopicAddDTO));
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResultVO delete(@RequestParam @ApiParam("applicationTopicId列表") Set<Long> applicationTopicIds) {
        applicationTopicService.deleteApplicationTopics(applicationTopicIds);
        return ResultVO.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ResultVO<ApplicationTopicCmsVO> update(@RequestBody @Valid ApplicationTopicCmsUpdateDTO applicationTopicUpdateDTO) {
        return ResultVO.successData(applicationTopicService.updateApplicationTopic(applicationTopicUpdateDTO));
    }

    @GetMapping("page")
    @ApiOperation("分页")
    public ResultVO<IPage<ApplicationTopicCmsVO>> page(@ModelAttribute ApplicationTopicCmsQueryDTO queryDTO, Page page) {
        return ResultVO.successData(applicationTopicService.findApplicationTopicPage(queryDTO, page));
    }

}

