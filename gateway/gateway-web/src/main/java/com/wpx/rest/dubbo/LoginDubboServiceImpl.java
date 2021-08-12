package com.wpx.rest.dubbo;

import com.wpx.gatewayweb.LoginDubboService;
import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenDTO;
import com.wpx.service.LoginService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 不会飞的小鹏
 * @Description: 登录服务dubbo接口
 */
@Service
public class LoginDubboServiceImpl implements LoginDubboService {

    @Autowired
    private LoginService loginService;

    @Override
    public ResultVO<LoginVO> login(LoginDTO loginDTO) {
        return ResultVO.successData(loginService.login(loginDTO.getUserId(), loginDTO.getRole()));
    }

    @Override
    public ResultVO<BooleanVO> logout(LogoutDTO logoutDTO) {
        loginService.logout(logoutDTO.getUserId());
        return ResultVO.successData(BooleanVO.result(true));
    }

    @Override
    public ResultVO<LoginVO> getUserIdInToken(TokenDTO tokenDTO) {
        return ResultVO.successData(loginService.getUserIdInToken(tokenDTO.getToken()));
    }

    @Override
    public ResultVO<BooleanVO> checkToken(TokenDTO tokenDTO) {
        return ResultVO.successData(loginService.checkToken(tokenDTO.getToken()));
    }

}
