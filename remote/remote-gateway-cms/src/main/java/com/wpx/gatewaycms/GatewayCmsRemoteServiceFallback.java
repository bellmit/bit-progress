package com.wpx.gatewaycms;

import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.login.TokenCheckDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 不会飞的小鹏
 * @Description: GatewayCmsRemoteService的fallback
 */
@Component
@Slf4j
public class GatewayCmsRemoteServiceFallback implements FallbackFactory<GatewayCmsRemoteService> {

    @Override
    public GatewayCmsRemoteService create(Throwable throwable) {

        log.error(GatewayCmsRemoteService.class.getSimpleName() + "error ", throwable);

        return new GatewayCmsRemoteService() {
            @Override
            public ResultVO<String> login(LoginDTO dto) {
                return null;
            }

            @Override
            public ResultVO logout(LogoutDTO dto) {
                return null;
            }

            @Override
            public ResultVO<String> getUserIdInToken(String token) {
                return null;
            }

            @Override
            public ResultVO<Boolean> checkToken(TokenCheckDTO checkDTO) {
                return null;
            }
        };
    }

}
