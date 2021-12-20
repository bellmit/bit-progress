package com.wpx.interceptor;

import com.wpx.exception.CommonException;
import com.wpx.exception.ExceptionMessage;
import com.wpx.util.StringUtils;
import com.wpx.property.ApplicationTokenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.wpx.constant.VerifyConstant.FEIGN_COMMON_TOKEN;
import static com.wpx.constant.VerifyConstant.ROUTE_REST_TOKEN;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/21 1:41
 * REST拦截器，检验对内部提供的接口token
 */
@Service
public class RestInterceptor implements HandlerInterceptor {

    @Autowired
    private ApplicationTokenProperties applicationTokenProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String routeRestToken = request.getHeader(ROUTE_REST_TOKEN);
        String restToken = applicationTokenProperties.getRest();
        if (StringUtils.equals(FEIGN_COMMON_TOKEN, routeRestToken) || StringUtils.equals(routeRestToken, restToken)) {
            return true;
        }
        throw new CommonException(ExceptionMessage.NON_INTERNAL_ACCESS_ERROR);
    }

}
