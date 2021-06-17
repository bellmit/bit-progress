package com.wpx.filter;

import com.alibaba.fastjson.JSON;
import com.wpx.exception.envm.AuthException;
import com.wpx.model.result.AuthResult;
import com.wpx.model.result.Result;
import com.wpx.nacos.service.NacosRouteMatchService;
import com.wpx.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wpx
 * Created on 2021/1/23 16:20
 * @description：
 */
@Configuration
public class AccessGatewayFilter implements GlobalFilter {

    @Autowired
    private AuthService authService;
    @Autowired
    private NacosRouteMatchService nacosRouteMatchService;

    private static final String USER_ID = "User-Id";

    /**
     * 1. 检验是否白名单或非api开头 {@link NacosRouteMatchService#ignoreAuthentication(String)}
     * 2. 检验token是否正确 {@link AuthService#checkToken(String)}
     *
     * @param exchange
     * @param chain
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String url = request.getPath().value();
        HttpHeaders headers = request.getHeaders();
        String authentication = headers.getFirst(HttpHeaders.AUTHORIZATION);

        if (nacosRouteMatchService.ignoreAuthentication(url)) {
            return chain.filter(exchange);
        }
        // 校验token
        AuthResult authResult = authService.checkToken(authentication);
        if (authResult.getResult()) {
            ServerHttpRequest httpRequest = request.mutate().header(USER_ID, authResult.getUserId()).build();
            ServerWebExchange cmsExchange = exchange.mutate().request(httpRequest).build();
            return chain.filter(cmsExchange);
        }

        return unauthorized(exchange, authResult.getAuthException());
    }

    /**
     * 网关拒绝，返回401
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange, AuthException authException) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory()
                .wrap(JSON.toJSONBytes(Result.error(authException)));
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
