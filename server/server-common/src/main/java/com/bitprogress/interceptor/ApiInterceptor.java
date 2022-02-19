package com.bitprogress.interceptor;

import com.bitprogress.constant.VerifyConstant;
import com.bitprogress.exception.CommonException;
import com.bitprogress.exception.ExceptionMessage;
import com.bitprogress.util.StringUtils;
import com.bitprogress.property.ApplicationTokenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/21 1:41
 * API拦截器，检验对外提供的接口token
 */
@Service
public class ApiInterceptor implements HandlerInterceptor {

    @Autowired
    private ApplicationTokenProperties applicationTokenProperties;

    /**
     * 对请求进行处理，检验是否由网关转发，并将网关追加到请求头的信息进行缓存
     *
     * @param request
     * @param response
     * @param handler
     * @return 处理是否通过
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String routeApiToken = request.getHeader(VerifyConstant.ROUTE_API_TOKEN);
        String apiToken = applicationTokenProperties.getApi();
        if (StringUtils.equals(routeApiToken, apiToken)) {
            return true;
        }
        throw new CommonException(ExceptionMessage.NON_GATEWAY_FORWARD_ERROR);
    }

}
