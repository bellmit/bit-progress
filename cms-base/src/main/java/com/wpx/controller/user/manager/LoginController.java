package com.wpx.controller.user.manager;

import com.wpx.common.model.ResultVO;
import com.wpx.model.user.manager.pojo.cms.LoginSuccessVO;
import com.wpx.model.user.manager.pojo.cms.ManagerLoginDTO;
import com.wpx.service.user.manager.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *
 * @author wpx
 **/
@RestController
@Api(tags = "系统 -- 登录模块")
@RequestMapping("/api/base/system/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @ApiOperation("登录")
    @PostMapping
    public ResultVO<LoginSuccessVO> login(@RequestBody @Valid ManagerLoginDTO loginDTO) {
        return ResultVO.success(loginService.login(loginDTO));
    }

}
