package com.wpx.nacos.service;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.wpx.okhttp.util.StringUtils;
import com.wpx.nacos.bean.RouteRouse;
import com.wpx.nacos.property.NacosGatewayProperties;
import com.wpx.nacos.route.GatewayRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * @author wpx
 * Created on 2021/1/25 10:09
 * @description：
 */
@Service
public class NacosDynamicRouteService implements ApplicationEventPublisherAware {

    private final Logger log = LoggerFactory.getLogger(NacosDynamicRouteService.class);

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Autowired
    private NacosGatewayProperties nacosGatewayProperties;

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    /**
     * 监听路由配置，如果获取到的配置为空则清空路由
     */
    @PostConstruct
    public void nacosRouteListener() {
        String routeDataId = nacosGatewayProperties.getRouteDataId();
        String group = nacosGatewayProperties.getGroup();
        String namespace = nacosDiscoveryProperties.getNamespace();
        String serverAddr = nacosDiscoveryProperties.getServerAddr();
        try {
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.NAMESPACE, namespace);
            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String config = configService.getConfig(routeDataId, group, 5000);

            // gateway启动初始化路由
            initRoute(config);

            configService.addListener(routeDataId, group, new Listener() {

                @Override
                public void receiveConfigInfo(String configInfo) {
                    clearRoute();
                    // 路由为空则不需要转换，直接return
                    if (StringUtils.isEmpty(configInfo)) {
                        return;
                    }
                    List<RouteDefinition> routeDefinitions = JSONArray.parseArray(configInfo, RouteDefinition.class);
                    routeDefinitions.forEach(NacosDynamicRouteService.this::addRoute);
                    publish();
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        }catch (NacosException e) {
            log.error("NacosRouteSetError ", e);
        }
    }

    /**
     * 监听路由白名单
     */
    @PostConstruct
    public void nacosWhiteRouteListener() {
        String whiteRouteDataId = nacosGatewayProperties.getWhiteRouteDataId();
        String group = nacosGatewayProperties.getGroup();
        String namespace = nacosDiscoveryProperties.getNamespace();
        String serverAddr = nacosDiscoveryProperties.getServerAddr();

        try {
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.NAMESPACE, namespace);
            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String config = configService.getConfig(whiteRouteDataId, group, 5000);

            // gateway启动初始化路由白名单
            initWhiteRoute(config);

            configService.addListener(whiteRouteDataId, group, new Listener() {

                @Override
                public void receiveConfigInfo(String configInfo) {
                    clearWhiteRoute();
                    // 如果内容为空则不需要转换，直接return
                    if (StringUtils.isEmpty(configInfo)) {
                        return;
                    }
                    List<String> whiteRoutes = JSONArray.parseArray(configInfo, String.class);
                    GatewayRoute.addWhiteRoute(whiteRoutes);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        }catch (NacosException e) {
            log.error("NacosWhiteRouteSetError ", e);
        }
    }

    /**
     * 监听路由资源列表
     */
    @PostConstruct
    public void nacosRouteRouseListener() {
        String routeRouseDataId = nacosGatewayProperties.getRouteRouseDataId();
        String group = nacosGatewayProperties.getGroup();
        String namespace = nacosDiscoveryProperties.getNamespace();
        String serverAddr = nacosDiscoveryProperties.getServerAddr();

        try {
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.NAMESPACE, namespace);
            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String config = configService.getConfig(routeRouseDataId, group, 5000);

            // gateway启动初始化资源
            initRouteRouse(config);

            configService.addListener(routeRouseDataId, group, new Listener() {

                @Override
                public void receiveConfigInfo(String configInfo) {
                    clearRouteRouse();
                    // 如果资源配置为空，则不需要配置
                    if (StringUtils.isEmpty(configInfo)) {
                        return;
                    }
                    List<RouteRouse> routeRouses = JSONArray.parseArray(configInfo, RouteRouse.class);
                    Map<String, List<String>> roleMap = routeRouses.stream().collect(
                            Collectors.toMap(NacosDynamicRouteService.this::getRouteRouseKey, RouteRouse::getRole));
                    if (!CollectionUtils.isEmpty(roleMap)) {
                        GatewayRoute.addRouteRouse(roleMap);
                    }
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        }catch (NacosException e) {
            log.error("NacosRouteRouseSetError ", e);
        }
    }

    /**
     * 监听路由ApiToken配置
     */
    @PostConstruct
    public void nacosRouteApiTokenListener() {
        String routeApiTokenDataId = nacosGatewayProperties.getRouteApiTokenDataId();
        String group = nacosGatewayProperties.getGroup();
        String namespace = nacosDiscoveryProperties.getNamespace();
        String serverAddr = nacosDiscoveryProperties.getServerAddr();

        try {
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.NAMESPACE, namespace);
            properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String config = configService.getConfig(routeApiTokenDataId, group, 5000);

            // gateway启动初始化资源
            initRouteApiToken(config);

            configService.addListener(routeApiTokenDataId, group, new Listener() {

                @Override
                public void receiveConfigInfo(String configInfo) {
                    clearRouteRouse();
                    // 如果资源配置为空，则不需要配置
                    if (StringUtils.isEmpty(configInfo)) {
                        return;
                    }
                    JSONObject apiTokenObject = JSON.parseObject(configInfo);
                    Map<String, String> apiTokenMap = new HashMap<>();
                    apiTokenObject.forEach((key, value) -> apiTokenMap.put(key, String.valueOf(value)));
                    GatewayRoute.addRouteApiTokenMap(apiTokenMap);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        }catch (NacosException e) {
            log.error("NacosRouteRouseSetError ", e);
        }
    }

    /**
     * gateway启动时初始化路由
     *
     * @param    configInfo
     */
    private void initRoute(String configInfo) {
        clearRoute();
        // 路由为空则不需要转换，直接return
        if (StringUtils.isEmpty(configInfo)) {
            return;
        }
        List<RouteDefinition> routeDefinitions = JSONArray.parseArray(configInfo, RouteDefinition.class);
        routeDefinitions.forEach(NacosDynamicRouteService.this::addRoute);
        publish();
    }

    /**
     * 初始化路由白名单
     *
     * @param    configInfo
     */
    private void initWhiteRoute(String configInfo) {
        clearWhiteRoute();
        // 如果内容为空则不需要转换，直接return
        if (StringUtils.isEmpty(configInfo)) {
            return;
        }
        List<String> whiteRoutes = JSONArray.parseArray(configInfo, String.class);
        GatewayRoute.addWhiteRoute(whiteRoutes);
    }

    /**
     * 初始化路由资源
     *
     * @param    configInfo
     */
    private void initRouteRouse(String configInfo) {
        clearRouteRouse();
        // 如果资源配置为空，则不需要配置
        if (StringUtils.isEmpty(configInfo)) {
            return;
        }
        List<RouteRouse> routeRouses = JSONArray.parseArray(configInfo, RouteRouse.class);
        Map<String, List<String>> roleMap = routeRouses.stream().
                collect(Collectors.toMap(this::getRouteRouseKey, RouteRouse::getRole));
        if (!CollectionUtils.isEmpty(roleMap)) {
            GatewayRoute.addRouteRouse(roleMap);
        }
    }

    /**
     * 初始化路由ApiToken
     *
     * @param    configInfo
     */
    private void initRouteApiToken(String configInfo) {
        clearRouteApiToken();
        // 如果资源配置为空，则不需要配置
        if (StringUtils.isEmpty(configInfo)) {
            return;
        }
        JSONObject apiTokenObject = JSON.parseObject(configInfo);
        Map<String, String> apiTokenMap = new HashMap<>();
        apiTokenObject.forEach((key, value) -> apiTokenMap.put(key, String.valueOf(value)));
        GatewayRoute.addRouteApiTokenMap(apiTokenMap);
    }

    /**
     * 清空路由
     */
    private void clearRoute() {
        List<String> routeList = GatewayRoute.getRouteList();
        routeList.forEach(this::deleteRoute);
        GatewayRoute.clearRouteList();
    }

    /**
     * 删除路由
     *
     * @param routeId
     */
    private void deleteRoute(String routeId) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(routeId)).subscribe();
        }catch (Exception e) {
            log.error("deleteRouteError routeId [{}] ", routeId, e);
        }
    }

    /**
     * 清空路由白名单
     */
    private void clearWhiteRoute() {
        GatewayRoute.clearWhiteRouteList();
    }

    /**
     * 清空路由资源
     */
    private void clearRouteRouse() {
        GatewayRoute.clearRouteRouseMap();
    }

    /**
     * 清空路由ApiToken
     */
    private void clearRouteApiToken() {
        GatewayRoute.clearRouteApiTokenMap();
    }

    /**
     * 添加路由
     *
     * @param    routeDefinition
     */
    private void addRoute(RouteDefinition routeDefinition) {
        String routeId = routeDefinition.getId();
        try {
            GatewayRoute.addRoute(routeId);
            routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        }catch (Exception e) {
            log.error("addRouteError routeId [{}] ", routeId, e);
        }
    }

    /**
     * 生成资源key
     *
     * @param    routeRouse
     * @return   String
     */
    private String getRouteRouseKey(RouteRouse routeRouse) {
        String method = routeRouse.getMethod().toUpperCase();
        String url = routeRouse.getUrl();
        return method + StringUtils.SPACE + url;
    }

    private void publish() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}
