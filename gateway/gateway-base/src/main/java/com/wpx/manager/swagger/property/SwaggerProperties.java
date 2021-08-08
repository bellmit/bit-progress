package com.wpx.manager.swagger.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 不会飞的小鹏
 * @Description: swagger配置类
 */
@Component
@ConfigurationProperties(prefix = SwaggerProperties.PREFIX)
public class SwaggerProperties {

    public static final String PREFIX = "swagger";

    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

}
