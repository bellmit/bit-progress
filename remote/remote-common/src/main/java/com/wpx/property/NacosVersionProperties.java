package com.wpx.property;

import com.wpx.common.util.StringUtils;
import com.wpx.constant.NacosConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spring.cloud.nacos")
public class NacosVersionProperties {

    /**
     * 需要版本配置的服务
     * key：服务名，value：版本信息
     * 如没有配置版本信息，默认使用default
     */
    private Map<String, String> version = new HashMap<>();

    /**
     * 获取版本控制信息
     */
    public Map<String, String> getVersion() {
        return this.version;
    }

    public void setVersion(Map<String, String> version) {
        this.version = version;
    }

    /**
     * 获取服务的版本控制信息
     *
     * @param serviceId
     */
    public String getVersionByServiceId(String serviceId) {
        String version = this.version.get(serviceId);
        return StringUtils.isEmpty(version) ? NacosConstant.DEFAULT : version;
    }

}
