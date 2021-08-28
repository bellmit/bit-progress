package com.wpx.controller.login;

import com.wpx.model.login.LoginVO;
import com.wpx.model.user.login.WechatLoginDTO;
import com.wpx.service.login.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LoginService loginService;

    @PostMapping("wechat")
    @ApiOperation("微信登录")
    public LoginVO wechatLogin(WechatLoginDTO wechatLoginDTO) {
        return loginService.wechatLogin(wechatLoginDTO);
    }

}
