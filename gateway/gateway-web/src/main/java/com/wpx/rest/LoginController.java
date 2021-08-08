package com.wpx.rest;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
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
     * @param dto 参数
     */
    @PostMapping("login")
    public ResultVO<String> login(@RequestBody UserLoginDTO dto) {
        return ResultVO.successData(loginService.login(dto.getUserId(), dto.getRole()));
    }

    /**
     * 退出登录
     *
     * @param dto  参数
     */
    @PostMapping("logout")
    public ResultVO<BooleanVO> logout(@RequestBody UserLogoutDTO dto) {
        loginService.logout(dto.getUserId());
        return ResultVO.successData(BooleanVO.result(true));
    }

    /**
     * 从token中解析userId
     *
     * @param token  需要解析的token
     */
    @GetMapping("userId")
    public ResultVO<String> getUserIdInToken(@RequestParam String token) {
        return ResultVO.successData(loginService.getUserIdInToken(token));
    }

    /**
     * 检验token是否有效
     *
     * @param checkDTO  检查的token
     */
    @PostMapping("checkToken")
    public ResultVO<Boolean> checkToken(@RequestBody TokenCheckDTO checkDTO) {
        return ResultVO.successData(loginService.checkToken(checkDTO.getToken()));
    }

}
