package com.wpx.gatewaycms;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenDTO;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author: 不会飞的小鹏
 */
@Service
public interface LoginDubboService {

    /**
     * 用户登录 -- 返回token
     *
     * @param loginDTO
     * @return: 返回token
     */
    ResultVO<LoginVO> login(LoginDTO loginDTO);

    /**
     * 退出登录
     *
     * @param logoutDTO
     * @return: 返回状态码
     */
    ResultVO<BooleanVO> logout(LogoutDTO logoutDTO);

    /**
     * 解析token获取用户的userId
     *
     * @param tokenDTO
     * @return: 返回userId
     */
    ResultVO<LoginVO> getUserIdInToken(TokenDTO tokenDTO);

    /**
     * 校验token是否有效
     *
     * @param tokenDTO 检查的token
     * @return: ResultVO<Boolean>
     */
    ResultVO<BooleanVO> checkToken(TokenDTO tokenDTO);

}
