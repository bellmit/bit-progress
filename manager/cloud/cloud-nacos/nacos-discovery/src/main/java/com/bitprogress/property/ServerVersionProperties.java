package com.bitprogress.property;

import com.bitprogress.constant.NacosConstant;
import com.bitprogress.util.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 不会飞的小鹏
 * 服务版本配置
 */
@ConfigurationProperties(prefix = ServerVersionProperties.PREFIX)
public class ServerVersionProperties {

    public static final String PREFIX = "spring.cloud";

    /**
     * 需要版本配置的服务
     * key：服务名，value：版本信息
     * 如没有配置版本信息，默认使用default
     */
    private Map<String, String> serverVersion = new HashMap<>();

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
