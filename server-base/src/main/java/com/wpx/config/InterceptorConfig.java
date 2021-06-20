package com.wpx.config;

import com.wpx.interceptor.ApiInterceptor;
import com.wpx.interceptor.RestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/21 3:45
 * @Description: 配置拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private ApiInterceptor apiInterceptor;
    @Autowired
    private RestInterceptor restInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**");
        registry.addInterceptor(restInterceptor).addPathPatterns("/rest/**");
    }

}
