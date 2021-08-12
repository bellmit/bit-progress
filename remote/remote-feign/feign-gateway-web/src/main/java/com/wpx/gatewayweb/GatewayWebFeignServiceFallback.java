package com.wpx.gatewayweb;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 不会飞的小鹏
 * @Description: GatewayWebRemoteService的服务降级类
 */
@Component
@Slf4j
public class GatewayWebFeignServiceFallback implements FallbackFactory<GatewayWebFeignService> {

    @Override
    public GatewayWebFeignService create(Throwable throwable) {
        return new GatewayWebFeignService() {
            @Override
            public ResultVO<LoginVO> login(LoginDTO dto) {
                return null;
            }

            @Override
            public ResultVO<BooleanVO> logout(LogoutDTO dto) {
                return null;
            }

            @Override
            public ResultVO<LoginVO> getUserIdInToken(String token) {
                return null;
            }

            @Override
            public ResultVO<BooleanVO> checkToken(TokenDTO checkDTO) {
                return null;
            }
        };
    }

}
