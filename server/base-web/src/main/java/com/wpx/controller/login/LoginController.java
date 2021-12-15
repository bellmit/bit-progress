package com.wpx.controller.login;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.TokenDTO;
import com.wpx.model.login.SmsCaptchaLoginDTO;
import com.wpx.model.login.WechatLoginDTO;
import com.wpx.service.login.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/26 14:23
 * 登录controller
 */
@Api(tags = "系统 -- 登录模块")
@RestController
@RequestMapping("api/base/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("smsCaptcha")
    @ApiOperation("短信验证码登录")
    public ResultVO<LoginVO> smsCaptchaLogin(SmsCaptchaLoginDTO smsCaptchaLoginDTO) {
        return ResultVO.successData(loginService.smsCaptchaLogin(smsCaptchaLoginDTO));
    }

    @GetMapping("tokenCheck")
    @ApiOperation("检查token")
    public ResultVO<BooleanVO> tokenCheck(TokenDTO tokenDTO) {
        return ResultVO.successData(loginService.tokenCheck(tokenDTO));
    }

    @PostMapping("wechat")
    @ApiOperation("微信登录")
    public ResultVO<LoginVO> wechatLogin(WechatLoginDTO wechatLoginDTO) {
        return ResultVO.successData(loginService.wechatLogin(wechatLoginDTO));
    }

}
