package com.wpx.gatewaycms.auth;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/14 19:53
 * @Description: Gateway模块的对外提供的授权和鉴权接口
 */
@FeignClient(name = "gateway-cms", path = "rest/gatewayCms/auth", fallbackFactory = AuthFeignServiceFallback.class)
public interface AuthFeignService {

    /**
     * 用户登录 -- 返回token
     *
     * @param loginDTO  登录信息DTO
     * @return: 返回token
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    ResultVO<LoginVO> login(@RequestBody LoginDTO loginDTO);

    /**
     * 退出登录
     *
     * @param logoutDTO  退出登录信息DTO
     * @return: 返回状态码
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    ResultVO<BooleanVO> logout(@RequestBody LogoutDTO logoutDTO);

    /**
     * 解析token获取用户的userId
     *
     * @param token  需要解析的token
     * @return: 返回LoginVO
     */
    @RequestMapping(value = "userId", method = RequestMethod.GET)
    ResultVO<LoginVO> getUserIdInToken(@RequestParam String token);

    /**
     * 校验token是否有效
     *
     * @param tokenDTO  检查的token
     * @return: ResultVO<BooleanVO>
     */
    @RequestMapping(value = "checkToken", method = RequestMethod.POST)
    ResultVO<BooleanVO> checkToken(@RequestBody TokenDTO tokenDTO);

}