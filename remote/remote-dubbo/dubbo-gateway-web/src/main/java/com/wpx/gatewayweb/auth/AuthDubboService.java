package com.wpx.gatewayweb.auth;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.LoginWebDTO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenDTO;

/**
 * @author 不会飞的小鹏
 * gateway-web dubbo远程服务
 */
public interface AuthDubboService {

    /**
     * 用户登录 -- 返回token
     *
     * @param dto
     * @return token
     */
    ResultVO<LoginVO> login(LoginWebDTO dto);

    /**
     * 退出登录
     *
     * @param dto
     * @return 状态码
     */
    ResultVO<BooleanVO> logout(LogoutDTO dto);

    /**
     * 解析token获取用户的userId
     *
     * @param tokenDTO
     * @return userId
     */
    ResultVO<LoginVO> getUserIdInToken(TokenDTO tokenDTO);

    /**
     * 校验token是否有效
     *
     * @param checkDTO
     * @return 是否通过
     */
    ResultVO<BooleanVO> checkToken(TokenDTO checkDTO);

}
