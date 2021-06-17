package com.wpx.nacos.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wpx
 * Created on 2021/1/26 16:56
 * @description：
 */
public class GatewayRoute {

    /**
     * 路由ID列表
     */
    private static final List<String> ROUTE_LIST = new ArrayList<>();

    /**
     * 路由白名单
     */
    private static final List<String> WHITE_ROUTE_LIST = new ArrayList<>();

    /**
     * 路由资源
     */
    private static final Map<String, List<String>> ROUTE_ROUSE_MAP = new HashMap<>();

    /**
     * 路由资源
     */
    private static final List<String> THIRD_ROUTE = new ArrayList<>();

    /**
     * 获取路由ID列表
     *
     * @return   List<String>
     */
    public static List<String> getRouteList() {
        return ROUTE_LIST;
    }

    /**
     * 获取路由白名单
     *
     * @return   List<String>
     */
    public static List<String> getWhiteRouteList() {
        return WHITE_ROUTE_LIST;
    }

    /**
     * 获取接口的资源列表
     *
     * @param    key
     * @return   List<String>
     */
    public static List<String> getRouteRouseList(String key) {
        return ROUTE_ROUSE_MAP.get(key);
    }

    /**
     * 获取三方路由
     *
     * @return   List<String>
     */
    public static List<String> getThirdRouteList() {
        return THIRD_ROUTE;
    }

    /**
     * 添加路由ID
     *
     * @param    route
     */
    public static void addRoute(String route) {
        ROUTE_LIST.add(route);
    }

    /**
     * 添加路由白名单
     *
     * @param    whiteRoutes
     */
    public static void addWhiteRoute(List<String> whiteRoutes) {
        WHITE_ROUTE_LIST.addAll(whiteRoutes);
    }

    /**
     * 添加路由资源
     *
     * @param    roleMap
     */
    public static void addRouteRouse(Map<String, List<String>> roleMap) {
        ROUTE_ROUSE_MAP.putAll(roleMap);
    }

    /**
     * 添加三方路由
     *
     * @param    thirdRoutes
     */
    public static void addThirdRoute(List<String> thirdRoutes) {
        THIRD_ROUTE.addAll(thirdRoutes);
    }

    /**
     * 清空路由ID
     */
    public static void clearRouteList() {
        ROUTE_LIST.clear();
    }

    /**
     * 清空路由白名单
     */
    public static void clearWhiteRouteList() {
        WHITE_ROUTE_LIST.clear();
    }

    /**
     * 清空路由资源
     */
    public static void clearRouteRouseMap() {
        ROUTE_ROUSE_MAP.clear();
    }

    /**
     * 清空三方路由
     */
    public static void clearThirdRouteList() {
        THIRD_ROUTE.clear();
    }

}
