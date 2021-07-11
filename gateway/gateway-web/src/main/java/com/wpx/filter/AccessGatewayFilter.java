package com.wpx.filter;

import com.alibaba.fastjson.JSON;
import com.wpx.common.constant.VerifyConstant;
import com.wpx.common.util.StringUtils;
import com.wpx.exception.envm.AuthException;
import com.wpx.model.result.AuthResult;
import com.wpx.model.result.Result;
import com.wpx.nacos.route.GatewayRoute;
import com.wpx.nacos.service.NacosRouteMatchService;
import com.wpx.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
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
            // 白名单，不需要检验token，使用-1作为userId
            return authorized(chain, exchange, request, StringUtils.MINUS_ONE);
        }
        // 校验token
        AuthResult authResult = authService.checkToken(authentication);
        if (authResult.getResult()) {
            return authorized(chain, exchange, request, authResult.getUserId());
        }

        return unauthorized(exchange, authResult.getAuthException());
    }

    /**
     * 通过检验，重新构建exchange
     */
    private Mono<Void> authorized(GatewayFilterChain chain,
                                  ServerWebExchange exchange,
                                  ServerHttpRequest request,
                                  String userId) {
        Route route =(Route) exchange.getAttributes().get(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String serverName = (String) route.getMetadata().get(VerifyConstant.SERVER_NAME);
        String routeApiToken = GatewayRoute.getRouteApiToken(serverName);
        ServerHttpRequest httpRequest = request.mutate()
                .header(VerifyConstant.USER_ID, userId)
                .header(VerifyConstant.ROUTE_API_TOKEN, routeApiToken)
                .build();
        ServerWebExchange cmsExchange = exchange.mutate().request(httpRequest).build();
        return chain.filter(cmsExchange);
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
