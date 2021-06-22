package com.wpx.interceptor;

import com.wpx.common.constant.VerifyConstant;
import com.wpx.property.ServerBaseProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/23 0:22
 * @Description feign服务调用都需要加上对应服务的token
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    @Autowired
    private ServerBaseProperties serverBaseProperties;

    /**
     * 为所有rest请求加上调用服务对应的token
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        String serverName = template.feignTarget().name();
        String serverToken = serverBaseProperties.getServerTokenByServerName(serverName);
        template.header(VerifyConstant.ROUTE_REST_TOKEN, serverToken);
    }

}
