package com.wpx.gatewaycms.auth;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.*;

/**
 * @author 不会飞的小鹏
 */
public interface AuthDubboService {

    /**
     * 用户登录 -- 返回token
     *
     * @param loginDTO  登录信息DTO
     * @return 返回token
     */
    ResultVO<LoginVO> login(LoginCmsDTO loginDTO);

    /**
     * 退出登录
     *
     * @param logoutDTO  退出登录信息DTO
     * @return 返回状态码
     */
    ResultVO<BooleanVO> logout(LogoutDTO logoutDTO);

    /**
     * 解析token获取用户的userId
     *
     * @param tokenDTO  需要解析的token
     * @return 返回userId
     */
    ResultVO<LoginVO> getUserIdInToken(TokenDTO tokenDTO);

    /**
     * 校验token是否有效
     *
     * @param tokenDTO 检查的token
     * @return ResultVO<Boolean>
     */
    ResultVO<BooleanVO> checkToken(TokenDTO tokenDTO);

}
