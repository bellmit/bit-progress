package com.wpx.controller.user;

import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/26 14:23
 * @Description 登录controller
 */
@Api(tags = "系统 -- 登录模块")
@RestController
@RequestMapping("api/base/login")
public class LoginController {

    @PostMapping("android")
    @ApiOperation("安卓登录")
    public LoginVO androidLogin(LoginDTO loginDTO) {
        return null;
    }

}
