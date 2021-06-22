package com.wpx.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/21 3:31
 * @Description ServerProperties is
 */
@Configuration
@ConfigurationProperties(prefix = "wpx.server.base")
public class ServerBaseProperties {

    /**
     * 调用服务配置rest-token
     */
    private Map<String, String> serverToken;

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

}
