package com.wpx.nacos.service;

import com.wpx.util.StringUtils;
import com.wpx.exception.envm.AuthException;
import com.wpx.model.result.AuthResult;
import com.wpx.nacos.route.GatewayRoute;
import com.wpx.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * @author 不会飞的小鹏
 */
@Service
public class NacosRouteMatchService {

    private AntPathMatcher matcher = new AntPathMatcher();

    /**
     * 接口访问权限控制
     *
     * @param authResult
     * @param method
     * @param url
     */
    public void checkRole(AuthResult authResult, String method, String url) {
        String key = method.toUpperCase() + StringUtils.SPACE + url;
        List<String> rouses = GatewayRoute.getRouteRouseList(key);
        if (CollectionUtils.nonEmpty(rouses)) {
            String role = authResult.getRole();
            boolean hasRole = rouses.contains(role);
            authResult.setResult(hasRole);
            if (!hasRole) {
                authResult.setAuthException(AuthException.AUTH_PERMISSION_FAILED);
            }
        }
    }

    /**
     * 查询是否为不校验路由
     *
     * @param  url
     * @return boolean
     */
    public boolean ignoreAuthentication(String url) {
        //如果为非/api/开头，一律通过
        if (StringUtils.startWithIgnoreCase(url, "/api/")) {
            // 获取路由白名单
            List<String> whiteRoutes = GatewayRoute.getWhiteRouteList();

            if (whiteRoutes.isEmpty()) {
                return false;
            }

            //判断与pattern是否匹配
            for (String ignoreUrlPattern : whiteRoutes) {
                if (pathMatch(ignoreUrlPattern, url)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }

    }

    /**
     * 路由规则匹配（ant格式）
     *
     * @param  pattern
     * @param  path
     */
    private boolean pathMatch(String pattern, String path) {
        AntPathMatcher pathMatcher = this.matcher;

        //使用空格分隔，e.g. GET /api/user，如果没有METHOD，直接取URI部分
        String pUri, pMethod, rUri, rMethod;

        String[] patternArray = pattern.split(" ");
        String[] pathArray = path.split(" ");

        if (patternArray.length < 2) {
            pMethod = null;
            pUri = patternArray[0];
        } else {
            pMethod = patternArray[0].toUpperCase();
            pUri = patternArray[1];
        }
        if (pathArray.length < 2) {
            rMethod = null;
            rUri = pathArray[0];
        }else {
            rMethod = pathArray[0];
            rUri = pathArray[1];
        }

        //如果pMethod为空，uri匹配，则匹配成功；若不为空，需要完全匹配
        return (null != pMethod && (pMethod.equals(rMethod) && pathMatcher.match(pUri, rUri)))
                || (null == pMethod && pathMatcher.match(pUri, rUri));
    }

}
