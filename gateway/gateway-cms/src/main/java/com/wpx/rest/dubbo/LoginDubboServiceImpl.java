package com.wpx.rest.dubbo;

import com.wpx.gatewaycms.LoginDubboService;
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

    /**
     * 用户登录 -- 返回token
     *
     * @param loginDTO  登录信息DTO
     * @return: 返回token
     */
    @Override
    public ResultVO<LoginVO> login(LoginDTO loginDTO) {
        return ResultVO.successData(loginService.login(loginDTO.getUserId(), loginDTO.getRole()));
    }

    /**
     * 退出登录
     *
     * @param logoutDTO  退出登录信息DTO
     * @return: 返回状态码
     */
    @Override
    public ResultVO<BooleanVO> logout(LogoutDTO logoutDTO) {
        loginService.logout(logoutDTO.getUserId());
        return ResultVO.successData(BooleanVO.result(true));
    }

    /**
     * 解析token获取用户的userId
     *
     * @param tokenDTO  需要解析的token
     * @return: 返回userId
     */
    @Override
    public ResultVO<LoginVO> getUserIdInToken(TokenDTO tokenDTO) {
        return ResultVO.successData(loginService.getUserIdInToken(tokenDTO.getToken()));
    }

    /**
     * 校验token是否有效
     *
     * @param tokenDTO 检查的token
     * @return: ResultVO<Boolean>
     */
    @Override
    public ResultVO<BooleanVO> checkToken(TokenDTO tokenDTO) {
        return ResultVO.successData(loginService.checkToken(tokenDTO.getToken()));
    }

}
