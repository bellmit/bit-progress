package com.wpx.controller.user.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.model.ResultVO;
import com.wpx.model.user.manager.pojo.cms.*;
import com.wpx.service.user.manager.ManagerService;
import com.wpx.okhttp.util.UserHelper;
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
    public ResultVO<ManagerCmsVO> findById(@RequestParam("managerId") Integer managerId) {
        return ResultVO.successData(managerService.findById(managerId, UserHelper.getUserId()));
    }

    @PostMapping
    @ApiOperation("添加")
    public ResultVO<ManagerCmsVO> add(@RequestBody @Valid ManagerCmsAddDTO addDTO) {
        return ResultVO.successData(managerService.add(addDTO));
    }

    @PutMapping
    @ApiOperation("修改")
    public ResultVO<ManagerCmsVO> update(@RequestBody @Valid ManagerCmsUpdateDTO managerUpdateDTO) {
        return ResultVO.successData(managerService.update(managerUpdateDTO));
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResultVO delete(@RequestParam @ApiParam("id列表") Set<Long> managerIds) {
        managerService.delete(managerIds, UserHelper.getUserId());
        return ResultVO.success();
    }

    @GetMapping("page")
    @ApiOperation("分页列表")
    public ResultVO<IPage<ManagerCmsVO>> findPage(@ModelAttribute ManagerCmsQueryDTO managerQueryVO, Page page) {
        return ResultVO.successData(managerService.findPage(managerQueryVO, page));
    }

    @PutMapping("password")
    @ApiOperation("重置密码")
    public ResultVO<ManagerCmsVO> resetPassword(@RequestBody @Valid ManagerResetPasswordDTO managerResetPasswordVO) {
        return ResultVO.successData(managerService.resetPassword(managerResetPasswordVO, UserHelper.getUserId()));
    }

    @PutMapping("state")
    @ApiOperation("禁用|开启")
    public ResultVO<ManagerCmsVO> handleDisabled(@RequestBody @Valid ManagerStateDTO managerStateVO) {
        return ResultVO.successData(managerService.handleDisabled(managerStateVO, UserHelper.getUserId()));
    }

    @PutMapping("role")
    @ApiOperation("禁用|开启")
    public ResultVO<ManagerCmsVO> handleRole(@RequestBody @Valid ManagerRoleDTO managerRoleVO) {
        return ResultVO.successData(managerService.handleRole(managerRoleVO));
    }

}
