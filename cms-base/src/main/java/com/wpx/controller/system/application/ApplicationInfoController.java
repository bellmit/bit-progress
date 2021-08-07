package com.wpx.controller.system.application;

import com.wpx.model.ResultVO;
import com.wpx.model.application.applicationinfo.pojo.cms.ApplicationInfoCmsAddDTO;
import com.wpx.model.application.applicationinfo.pojo.cms.ApplicationInfoCmsQueryDTO;
import com.wpx.model.application.applicationinfo.pojo.cms.ApplicationInfoCmsUpdateDTO;
import com.wpx.model.application.applicationinfo.pojo.cms.ApplicationInfoCmsVO;
import com.wpx.service.application.ApplicationInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.validation.Valid;
import java.util.Set;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 不会飞的小鹏 
 * @since 2021-06-27
 */
@Api(tags = {"系统 -- 应用配置信息"})
@RestController
@RequestMapping("/api/base/system/applicationInfo")
public class ApplicationInfoController {

    @Autowired
    private ApplicationInfoService applicationInfoService;

    @GetMapping
    @ApiOperation("查询详情")
    public ResultVO<ApplicationInfoCmsVO> findById(@RequestParam @ApiParam("applicationInfoId") Long applicationInfoId) {
        return ResultVO.successData(applicationInfoService.findById(applicationInfoId));
    }

    @PostMapping
    @ApiOperation("添加")
    public ResultVO<ApplicationInfoCmsVO> save(@RequestBody @Valid ApplicationInfoCmsAddDTO applicationInfoAddDTO) {
        return ResultVO.successData(applicationInfoService.saveApplicationInfo(applicationInfoAddDTO));
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResultVO delete(@RequestParam @ApiParam("applicationInfoId列表") Set<Long> applicationInfoIds) {
        applicationInfoService.deleteApplicationInfos(applicationInfoIds);
        return ResultVO.success();
    }

    @PutMapping
    @ApiOperation("修改")
    public ResultVO<ApplicationInfoCmsVO> update(@RequestBody @Valid ApplicationInfoCmsUpdateDTO applicationInfoUpdateDTO) {
        return ResultVO.successData(applicationInfoService.updateApplicationInfo(applicationInfoUpdateDTO));
    }

    @GetMapping("page")
    @ApiOperation("分页")
    public ResultVO<IPage<ApplicationInfoCmsVO>> page(@ModelAttribute ApplicationInfoCmsQueryDTO queryDTO, Page page) {
        return ResultVO.successData(applicationInfoService.findApplicationInfoPage(queryDTO, page));
    }
}

