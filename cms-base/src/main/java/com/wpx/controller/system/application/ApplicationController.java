package com.wpx.controller.system.application;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.common.model.ResultVO;
import com.wpx.model.system.application.pojo.ApplicationMessageVO;
import com.wpx.model.system.application.pojo.cms.*;
import com.wpx.service.system.application.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * @author wupengxiao
 * @since 2021-06-07
 */
@Api(tags = {"系统 -- 应用信息"})
@RestController
@RequestMapping("/api/base/system/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    @ApiOperation("查询详情")
    public ResultVO<ApplicationCmsVO> findById(@RequestParam @ApiParam("applicationId") Long applicationId) {
        return ResultVO.success(applicationService.findById(applicationId));
    }

    @PostMapping
    @ApiOperation("添加")
    public ResultVO<ApplicationCmsVO> save(@RequestBody @Valid ApplicationCmsAddDTO applicationAddDTO) {
        return ResultVO.success(applicationService.saveApplication(applicationAddDTO));
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResultVO delete(@RequestParam @ApiParam("applicationId列表") Set<Long> applicationIds) {
        applicationService.deleteApplications(applicationIds);
        return ResultVO.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ResultVO<ApplicationCmsVO> update(@RequestBody @Valid ApplicationCmsUpdateDTO applicationUpdateDTO) {
        return ResultVO.success(applicationService.updateApplication(applicationUpdateDTO));
    }

    @PutMapping("disabled")
    @ApiOperation("禁用和启用应用信息")
    public ResultVO<ApplicationCmsVO> disabled(@RequestBody @Valid ApplicationCmsDisabledDTO disabledDTO) {
        return ResultVO.success(applicationService.disabled(disabledDTO));
    }

    @GetMapping("page")
    @ApiOperation("分页")
    public ResultVO<IPage<ApplicationCmsVO>> page(@ModelAttribute ApplicationCmsQueryDTO applicationQueryDTO, Page page) {
        return ResultVO.success(applicationService.findApplicationPage(applicationQueryDTO, page));
    }

    @GetMapping("list")
    @ApiOperation("应用列表")
    public ResultVO<List<ApplicationMessageVO>> list() {
        return ResultVO.success(applicationService.listApplicationMessage());
    }

}

