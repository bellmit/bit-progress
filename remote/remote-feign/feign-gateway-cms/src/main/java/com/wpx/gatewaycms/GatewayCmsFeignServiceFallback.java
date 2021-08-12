package com.wpx.gatewaycms;

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
 * @Description: GatewayCmsRemoteService的fallback
 */
@Component
@Slf4j
public class GatewayCmsFeignServiceFallback implements FallbackFactory<GatewayCmsFeignService> {

    @Override
    public GatewayCmsFeignService create(Throwable throwable) {

        log.error(GatewayCmsFeignService.class.getSimpleName() + "error ", throwable);

        return new GatewayCmsFeignService() {
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
