package com.wpx.util;

import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

/**
 * @author 不会飞的小鹏
 * create on 2021/7/11 1:13
 * @Description
 */
public class ReactiveRequestContextHolder {

    public static final Class<ServerHttpRequest> CONTEXT_KEY = ServerHttpRequest.class;

    public static Mono<ServerHttpRequest> getRequest() {
        return Mono.subscriberContext().map(ctx -> ctx.get(CONTEXT_KEY));
    }

}
