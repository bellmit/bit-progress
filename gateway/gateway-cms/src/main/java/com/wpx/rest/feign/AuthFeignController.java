package com.wpx.rest.feign;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenDTO;
import com.wpx.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 不会飞的小鹏
 * @Description: Cms端登录调用接口
 */
@RestController
@RequestMapping("rest/gatewayCms/auth")
public class AuthFeignController {

    @Autowired
    private AuthService authService;

    /**
     * 用户登录获取token
     *
     * @param loginDTO  登录信息DTO
     */
    @PostMapping("login")
    public ResultVO<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        return ResultVO.successData(authService.login(loginDTO.getUserId(), loginDTO.getRole()));
    }

    /**
     * 退出登录
     *
     * @param logoutDTO  退出登录信息DTO
     */
    @PostMapping("logout")
    public ResultVO<BooleanVO> logout(@RequestBody LogoutDTO logoutDTO) {
        authService.logout(logoutDTO.getUserId());
        return ResultVO.successData(BooleanVO.result(true));
    }

    /**
     * 从token中解析userId
     *
     * @param tokenDTO  需要解析的token
     */
    @GetMapping("userId")
    public ResultVO<LoginVO> getUserIdInToken(@ModelAttribute TokenDTO tokenDTO) {
        return ResultVO.successData(authService.getUserIdInToken(tokenDTO.getToken()));
    }

    /**
     * 检验token是否有效
     *
     * @param tokenDTO  需要检查的token
     */
    @PostMapping("checkToken")
    public ResultVO<BooleanVO> checkToken(@RequestBody TokenDTO tokenDTO) {
        return ResultVO.successData(BooleanVO.result(authService.checkToken(tokenDTO.getToken()).getResult()));
    }

}
