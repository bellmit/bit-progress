package com.wpx.rest.dubbo;

import com.wpx.gatewaycms.auth.AuthDubboService;
import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
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

    /**
     * 用户登录 -- 返回token
     *
     * @param loginDTO  登录信息DTO
     * @return 返回token
     */
    @Override
    public ResultVO<LoginVO> login(LoginDTO loginDTO) {
        return ResultVO.successData(authService.login(loginDTO.getUserId(), loginDTO.getRole()));
    }

    /**
     * 退出登录
     *
     * @param logoutDTO  退出登录信息DTO
     * @return 返回状态码
     */
    @Override
    public ResultVO<BooleanVO> logout(LogoutDTO logoutDTO) {
        authService.logout(logoutDTO.getUserId());
        return ResultVO.successData(BooleanVO.result(true));
    }

    /**
     * 解析token获取用户的userId
     *
     * @param tokenDTO  需要解析的token
     * @return 返回userId
     */
    @Override
    public ResultVO<LoginVO> getUserIdInToken(TokenDTO tokenDTO) {
        return ResultVO.successData(authService.getUserIdInToken(tokenDTO.getToken()));
    }

    /**
     * 校验token是否有效
     *
     * @param tokenDTO 检查的token
     * @return ResultVO<Boolean>
     */
    @Override
    public ResultVO<BooleanVO> checkToken(TokenDTO tokenDTO) {
        return ResultVO.successData(BooleanVO.result(authService.checkToken(tokenDTO.getToken()).getResult()));
    }

}
