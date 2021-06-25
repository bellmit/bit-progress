package com.wpx.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/21 3:31
 * @Description ServerProperties is
 */
@Configuration
@ConfigurationProperties(prefix = "spring.cloud.nacos")
public class ServerBaseProperties {

    /**
     * 对外接口token
     */
    private String apiToken;

    /**
     * 内部服务接口token
     */
    private String restToken;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getRestToken() {
        return restToken;
    }

    public void setRestToken(String restToken) {
        this.restToken = restToken;
    }

}
