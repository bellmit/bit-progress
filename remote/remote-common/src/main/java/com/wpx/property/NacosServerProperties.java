package com.wpx.property;

import com.wpx.common.util.StringUtils;
import com.wpx.constant.NacosConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/21 3:31
 * @Description ServerProperties is
 */
@Configuration
@ConfigurationProperties(prefix = "spring.cloud.nacos")
public class NacosServerProperties {

    /**
     * 调用服务配置rest-token
     */
    private Map<String, String> serverToken;

    /**
     * 需要版本配置的服务
     * key：服务名，value：版本信息
     * 如没有配置版本信息，默认使用default
     */
    private Map<String, String> serverVersion = new HashMap<>();

    public Map<String, String> getServerToken() {
        return serverToken;
    }

    public void setServerToken(Map<String, String> serverToken) {
        this.serverToken = serverToken;
    }

    /**
     * 通过服务名获取调用服务需要的restToken
     *
     * @param serverName
     */
    public String getServerTokenByServerName(String serverName) {
        return this.serverToken.get(serverName);
    }

    /**
     * 获取版本控制信息
     */
    public Map<String, String> getServerVersion() {
        return this.serverVersion;
    }

    public void setServerVersion(Map<String, String> serverVersion) {
        this.serverVersion = serverVersion;
    }

    /**
     * 获取服务的版本控制信息
     *
     * @param serviceId
     */
    public String getServerVersionByServiceId(String serviceId) {
        String version = this.serverVersion.get(serviceId);
        return StringUtils.isEmpty(version) ? NacosConstant.DEFAULT : version;
    }

}
