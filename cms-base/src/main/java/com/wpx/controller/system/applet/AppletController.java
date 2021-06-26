package com.wpx.controller.system.applet;

import com.wpx.model.system.applet.pojo.cms.AppletCmsAddDTO;
import com.wpx.model.system.applet.pojo.cms.AppletCmsQueryDTO;
import com.wpx.model.system.applet.pojo.cms.AppletCmsUpdateDTO;
import com.wpx.model.system.applet.pojo.cms.AppletCmsVO;
import com.wpx.service.system.applet.AppletService;
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
 * @author wupengxiao
 * @since 2021-06-26
 */
@Api(tags = {"小程序信息"})
@RestController
@RequestMapping("/api/applet")
public class AppletController {

    @Autowired
    private AppletService appletService;

    @GetMapping
    @ApiOperation("查询详情")
    public AppletCmsVO findById(@RequestParam @ApiParam("appletId") Long appletId) {
        return appletService.findById(appletId);
    }

    @PostMapping
    @ApiOperation("添加")
    public AppletCmsVO save(@RequestBody @Valid AppletCmsAddDTO appletAddDTO) {
        return appletService.saveApplet(appletAddDTO);
    }

    @DeleteMapping
    @ApiOperation("删除")
    public void delete(@RequestParam @ApiParam("appletId列表") Set<Integer> appletIds) {
        appletService.deleteApplets(appletIds);
    }

    @PutMapping
    @ApiOperation("修改")
    public AppletCmsVO update(@RequestBody @Valid AppletCmsUpdateDTO appletUpdateDTO) {
        return appletService.updateApplet(appletUpdateDTO);
    }

    @GetMapping("page")
    @ApiOperation("分页")
    public IPage<AppletCmsVO> page(@ModelAttribute AppletCmsQueryDTO appletQueryDTO, Page page) {
        return appletService.findAppletPage(appletQueryDTO, page);
    }
}

