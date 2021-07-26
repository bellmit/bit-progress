package com.wpx.gatewaycms;

import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenCheckDTO;
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
@FeignClient(name = "gateway-web", fallbackFactory = GatewayCmsRemoteServiceFallback.class)
public interface GatewayCmsRemoteService {

    /**
     * 用户登录 -- 返回token
     *
     * @param dto
     * @return: 返回token
     */
    @RequestMapping(value = "rest/auth/login", method = RequestMethod.POST)
    ResultVO<String> login(@RequestBody LoginDTO dto);

    /**
     * 退出登录
     *
     * @param dto
     * @return: 返回状态码
     */
    @RequestMapping(value = "rest/auth/logout", method = RequestMethod.POST)
    ResultVO logout(@RequestBody LogoutDTO dto);

    /**
     * 解析token获取用户的userId
     *
     * @param token
     * @return: 返回userId
     */
    @RequestMapping(value = "rest/auth/userId", method = RequestMethod.GET)
    ResultVO<String> getUserIdInToken(@RequestParam String token);

    /**
     * 校验token是否有效
     *
     * @param checkDTO
     */
    @RequestMapping(value = "rest/auth/checkToken", method = RequestMethod.POST)
    ResultVO<Boolean> checkToken(@RequestBody TokenCheckDTO checkDTO);

}
