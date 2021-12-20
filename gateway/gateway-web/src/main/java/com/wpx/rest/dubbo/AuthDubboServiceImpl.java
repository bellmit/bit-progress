package com.wpx.rest.dubbo;

import com.wpx.gatewayweb.auth.AuthDubboService;
import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.LoginWebDTO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenDTO;
import com.wpx.service.AuthService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 不会飞的小鹏
 * 登录服务dubbo接口
 */
@Service(version = "1.0.0")
public class AuthDubboServiceImpl implements AuthDubboService {

    @Autowired
    private AuthService authService;

    @Override
    public ResultVO<LoginVO> login(LoginWebDTO loginDTO) {
        return ResultVO.successData(authService.login(loginDTO.getUserId(), loginDTO.getAuthMsg()));
    }

    @Override
    public ResultVO<BooleanVO> logout(LogoutDTO logoutDTO) {
        authService.logout(logoutDTO.getUserId());
        return ResultVO.successData(BooleanVO.result(true));
    }

    @Override
    public ResultVO<LoginVO> getUserIdInToken(TokenDTO tokenDTO) {
        return ResultVO.successData(authService.getUserIdInToken(tokenDTO.getToken()));
    }

    @Override
    public ResultVO<BooleanVO> checkToken(TokenDTO tokenDTO) {
        return ResultVO.successData(BooleanVO.result(authService.checkToken(tokenDTO.getToken()).getResult()));
    }

}
