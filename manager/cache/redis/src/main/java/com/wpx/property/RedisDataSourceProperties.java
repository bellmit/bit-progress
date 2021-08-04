package com.wpx.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: redis多数据源配置
 */
@ConfigurationProperties(prefix = RedisDataSourceProperties.PREFIX)
public class RedisDataSourceProperties {

    public static final String PREFIX = "spring.redis";

    private Map<String, RedisMessageProperties> redisDataSource;

    public Map<String, RedisMessageProperties> getRedisDataSource() {
        return redisDataSource;
    }

    public void setRedisDataSource(Map<String, RedisMessageProperties> redisDataSource) {
        this.redisDataSource = redisDataSource;
    }

}
