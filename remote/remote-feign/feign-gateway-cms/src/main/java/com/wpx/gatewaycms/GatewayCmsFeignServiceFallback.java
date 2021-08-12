package com.wpx.gatewaycms;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 不会飞的小鹏
 * @Description: GatewayCmsRemoteService的fallback
 */
@Component
public class GatewayCmsFeignServiceFallback implements FallbackFactory<GatewayCmsFeignService> {

    @Override
    public GatewayCmsFeignService create(Throwable throwable) {

        return new GatewayCmsFeignService() {
            @Override
            public ResultVO<LoginVO> login(LoginDTO loginDTO) {
                return null;
            }

            @Override
            public ResultVO<BooleanVO> logout(LogoutDTO logoutDTO) {
                return null;
            }

            @Override
            public ResultVO<LoginVO> getUserIdInToken(String token) {
                return null;
            }

            @Override
            public ResultVO<BooleanVO> checkToken(TokenDTO tokenDTO) {
                return null;
            }
        };
    }

}
