package com.wpx.controller.system.login;

import com.wpx.model.login.LoginDTO;
import com.wpx.model.user.login.LoginVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/26 14:23
 * @Description 登录controller
 */
@RestController
@RequestMapping("api/base/system/login")
public class LoginController {

    @PostMapping("android")
    public LoginVO androidLogin(LoginDTO loginDTO) {
        return null;
    }

}
