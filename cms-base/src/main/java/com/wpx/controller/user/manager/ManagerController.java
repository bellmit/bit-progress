package com.wpx.controller.user.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.model.user.manager.pojo.cms.*;
import com.wpx.service.user.manager.ManagerService;
import com.wpx.util.UserHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author wpx
 **/
@RestController
@Api(tags = "系统 -- 管理员模块")
@RequestMapping("/api/base/system/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    @ApiOperation("获取管理员信息")
    public ManagerCmsVO findById(@RequestParam("managerId") Integer managerId) {
        return managerService.findById(managerId, UserHelper.getUserId());
    }

    @PostMapping
    @ApiOperation("添加")
    public ManagerCmsVO add(@RequestBody @Valid ManagerCmsAddDTO addDTO) {
        return managerService.add(addDTO);
    }

    @PutMapping
    @ApiOperation("修改")
    public ManagerCmsVO update(@RequestBody @Valid ManagerCmsUpdateDTO managerUpdateDTO) {
        return managerService.update(managerUpdateDTO);
    }

    @DeleteMapping
    @ApiOperation("删除")
    public void delete(@RequestParam @ApiParam("id列表") Set<Long> managerIds) {
        managerService.delete(managerIds, UserHelper.getUserId());
    }

    @GetMapping("page")
    @ApiOperation("分页列表")
    public IPage<ManagerCmsVO> findPage(@ModelAttribute ManagerCmsQueryDTO managerQueryVO, Page page) {
        return managerService.findPage(managerQueryVO, page);
    }

    @PutMapping("password")
    @ApiOperation("重置密码")
    public ManagerCmsVO resetPassword(@RequestBody @Valid ManagerResetPasswordDTO managerResetPasswordVO) {
        return managerService.resetPassword(managerResetPasswordVO, UserHelper.getUserId());
    }

    @PutMapping("state")
    @ApiOperation("禁用|开启")
    public ManagerCmsVO handleDisabled(@RequestBody @Valid ManagerStateDTO managerStateVO) {
        return managerService.handleDisabled(managerStateVO, UserHelper.getUserId());
    }

    @PutMapping("role")
    @ApiOperation("禁用|开启")
    public ManagerCmsVO handleRole(@RequestBody @Valid ManagerRoleDTO managerRoleVO) {
        return managerService.handleRole(managerRoleVO);
    }

}
