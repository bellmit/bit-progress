package com.wpx.filter;

import com.alibaba.fastjson.JSON;
import com.wpx.constant.VerifyConstant;
import com.wpx.model.login.AuthCmsMsg;
import com.wpx.route.GatewayRoute;
import com.wpx.service.AuthorizeService;
import com.wpx.util.StringUtils;
import com.wpx.exception.envm.AuthException;
import com.wpx.model.result.AuthResult;
import com.wpx.model.result.Result;
import com.wpx.service.RouteMatchService;
import com.wpx.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
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

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_LOADBALANCER_RESPONSE_ATTR;

/**
 * @author wpx
 * Created on 2021/1/23 16:20
 */
@Configuration
public class AccessGatewayFilter implements GlobalFilter {

    @Autowired
    private AuthorizeService authorizeService;
    @Autowired
    private RouteMatchService routeMatchService;

    /**
     * 1. 检验是否白名单或非api开头 {@link RouteMatchService#ignoreAuthentication(String, String)}
     * 2. 检验token是否正确 {@link AuthService#checkToken(String)}
     *
     * @param exchange exchange信息
     * @param chain    chain信息
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String url = request.getPath().value();
        String method = request.getMethodValue();
        HttpHeaders headers = request.getHeaders();
        String authentication = headers.getFirst(HttpHeaders.AUTHORIZATION);

        if (routeMatchService.ignoreAuthentication(method, url)) {
            return authorized(chain, exchange, request, StringUtils.MINUS_ONE);
        }
        // 校验token
        AuthResult<AuthCmsMsg> authResult = authorizeService.checkToken(authentication, AuthCmsMsg.class);
        if (authResult.getResult()) {
            return authorized(chain, exchange, request, authResult.getUserId());
        }

        return unauthorized(exchange, authResult.getAuthException());
    }

    /**
     * 通过检验，重新构建exchange
     * 将解析token获取的userId以及服务元数据中的中token放到ServerHttpRequest的header中，用以标识是通过网关转发的请求
     */
    private Mono<Void> authorized(GatewayFilterChain chain,
                                  ServerWebExchange exchange,
                                  ServerHttpRequest request,
                                  String userId) {
        DefaultResponse response = (DefaultResponse) exchange.getAttributes().get(GATEWAY_LOADBALANCER_RESPONSE_ATTR);
        String serviceId = response.getServer().getServiceId();
        String routeApiToken = GatewayRoute.getRouteApiToken(serviceId);
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
