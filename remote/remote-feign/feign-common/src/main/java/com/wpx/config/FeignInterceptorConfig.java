package com.wpx.config;

import com.wpx.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/23 0:22
 * @Description feign请求过滤器配置
 */
@Configuration
public class FeignInterceptorConfig {

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return new FeignRequestInterceptor();
    }

}
