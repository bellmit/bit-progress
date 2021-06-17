package com.wpx.rest;

import com.wpx.model.login.TokenCheckDTO;
import com.wpx.model.login.UserLoginDTO;
import com.wpx.model.login.UserLogoutDTO;
import com.wpx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wpx
 * Created on 2021/1/26 16:03
 * @description：
 */
@RestController
@RequestMapping("rest/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录获取token
     *
     * @param dto
     */
    @PostMapping("login")
    public String login(@RequestBody UserLoginDTO dto) {
        return loginService.login(dto.getUserId(), dto.getRole());
    }

    /**
     * 退出登录
     *
     * @param dto
     */
    @PostMapping("logout")
    public void logout(@RequestBody UserLogoutDTO dto) {
        loginService.logout(dto.getUserId());
    }

    /**
     * 从token中解析userId
     *
     * @param token
     */
    @GetMapping("userId")
    public String getUserIdInToken(@RequestParam String token) {
        return loginService.getUserIdInToken(token);
    }

    /**
     * 检验token是否有效
     *
     * @param checkDTO
     */
    @PostMapping("checkToken")
    public Boolean checkToken(@RequestBody TokenCheckDTO checkDTO) {
        return loginService.checkToken(checkDTO.getToken());
    }

}
