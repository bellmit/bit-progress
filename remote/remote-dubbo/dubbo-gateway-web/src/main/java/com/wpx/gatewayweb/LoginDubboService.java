package com.wpx.gatewayweb;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenDTO;

/**
 * @Author: 不会飞的小鹏
 * @Description: gateway-web dubbo远程服务
 */
public interface LoginDubboService {

    /**
     * 用户登录 -- 返回token
     *
     * @param dto
     * @return: token
     */
    ResultVO<LoginVO> login(LoginDTO dto);

    /**
     * 退出登录
     *
     * @param dto
     * @return: 状态码
     */
    ResultVO<BooleanVO> logout(LogoutDTO dto);

    /**
     * 解析token获取用户的userId
     *
     * @param tokenDTO
     * @return: userId
     */
    ResultVO<LoginVO> getUserIdInToken(TokenDTO tokenDTO);

    /**
     * 校验token是否有效
     *
     * @param checkDTO
     * @return: 是否通过
     */
    ResultVO<BooleanVO> checkToken(TokenDTO checkDTO);

}
