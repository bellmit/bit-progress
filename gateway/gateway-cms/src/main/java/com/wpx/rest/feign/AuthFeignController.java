package com.wpx.rest.feign;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginCmsDTO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenDTO;
import com.wpx.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 不会飞的小鹏
 * Cms端登录调用接口
 */
@RestController
@RequestMapping("rest/gatewayCms/auth")
public class AuthFeignController {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * 用户登录获取token
     *
     * @param loginDTO  登录信息DTO
     */
    @PostMapping("login")
    public ResultVO<LoginVO> login(@RequestBody LoginCmsDTO loginDTO) {
        return ResultVO.successData(authorizationService.login(loginDTO.getUserId()));
    }

    /**
     * 退出登录
     *
     * @param logoutDTO  退出登录信息DTO
     */
    @PostMapping("logout")
    public ResultVO<BooleanVO> logout(@RequestBody LogoutDTO logoutDTO) {
        authorizationService.logout(logoutDTO.getUserId());
        return ResultVO.successData(BooleanVO.result(true));
    }

    /**
     * 从token中解析userId
     *
     * @param tokenDTO  需要解析的token
     */
    @GetMapping("userId")
    public ResultVO<LoginVO> getUserIdInToken(@ModelAttribute TokenDTO tokenDTO) {
        return ResultVO.successData(authorizationService.getUserIdInToken(tokenDTO.getToken()));
    }

    /**
     * 检验token是否有效
     *
     * @param tokenDTO  需要检查的token
     */
    @PostMapping("checkToken")
    public ResultVO<BooleanVO> checkToken(@RequestBody TokenDTO tokenDTO) {
        return ResultVO.successData(BooleanVO.result(authorizationService.checkToken(tokenDTO.getToken()).getResult()));
    }

}
