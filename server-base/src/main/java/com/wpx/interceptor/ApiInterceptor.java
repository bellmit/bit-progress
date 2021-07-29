package com.wpx.interceptor;

import com.wpx.constant.VerifyConstant;
import com.wpx.exception.CustomizeException;
import com.wpx.exception.ExceptionMessage;
import com.wpx.util.StringUtils;
import com.wpx.property.ServerBaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/21 1:41
 * @Description API拦截器，检验对外提供的接口token
 */
@Service
public class ApiInterceptor implements HandlerInterceptor {

    @Autowired
    private ServerBaseProperties serverBaseProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String routeApiToken = request.getHeader(VerifyConstant.ROUTE_API_TOKEN);
        String apiToken = serverBaseProperties.getApiToken();
        if (StringUtils.equals(routeApiToken, apiToken)) {
            return true;
        }
        throw new CustomizeException(ExceptionMessage.AUTH_TOKEN_WRONG);
    }

}
