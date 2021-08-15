package com.wpx.controller.app;

import com.wpx.model.BooleanVO;
import com.wpx.model.app.app.pojo.cms.AppCmsAddDTO;
import com.wpx.model.app.app.pojo.cms.AppCmsQueryDTO;
import com.wpx.model.app.app.pojo.cms.AppCmsUpdateDTO;
import com.wpx.model.app.app.pojo.cms.AppCmsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.model.ResultVO;

import javax.validation.Valid;
import java.util.Set;
import com.wpx.service.app.AppService;
import org.springframework.web.bind.annotation.RestController;

/**
* @author 不会飞的小鹏
*/
@Api(tags = {"应用信息"})
@RestController
@RequestMapping("/api/base/app/app")
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping
    @ApiOperation("查询详情")
    public ResultVO<AppCmsVO> findById(@RequestParam @ApiParam("appId") Long appId) {
        return ResultVO.successData(appService.findById(appId));
    }

    @PostMapping
    @ApiOperation("添加")
    public ResultVO<AppCmsVO> save(@RequestBody @Valid AppCmsAddDTO appAddDTO) {
        return ResultVO.successData(appService.saveApp(appAddDTO));
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResultVO<BooleanVO> delete(@RequestParam @ApiParam("appId列表") Set<Long> appIds) {
        appService.deleteApps(appIds);
        return ResultVO.successData(BooleanVO.result(true));
    }

    @PutMapping
    @ApiOperation("修改")
    public ResultVO<AppCmsVO> update(@RequestBody @Valid AppCmsUpdateDTO appUpdateDTO) {
         return ResultVO.successData(appService.updateApp(appUpdateDTO));
    }

    @GetMapping("page")
    @ApiOperation("分页")
    public ResultVO<IPage<AppCmsVO>> page(@ModelAttribute AppCmsQueryDTO appQueryDTO, Page page) {
        return ResultVO.successData(appService.findAppPage(appQueryDTO,page));
    }

}

