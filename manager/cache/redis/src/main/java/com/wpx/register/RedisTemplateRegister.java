package com.wpx.register;

import com.wpx.property.RedisDataSourceProperties;
import com.wpx.property.RedisMessageProperties;
import com.wpx.util.CollectionUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 初始化RedisTemplate并交给Spring管理
 */
@Component
public class RedisTemplateRegister implements BeanFactoryAware {

    @Autowired
    private RedisDataSourceProperties redisDataSourceProperties;

    /**
     * 读取redis配置并创建对应的StringRedisTemplate
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        Map<String, RedisMessageProperties> redisDataSource = redisDataSourceProperties.getDataSource();
        if (CollectionUtils.nonEmpty(redisDataSource)) {
            redisDataSource.forEach((name, dataSource) -> {
                StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
                RedisConnectionFactory redisConnection = getRedisConnection(dataSource);
                stringRedisTemplate.setConnectionFactory(redisConnection);
                stringRedisTemplate.afterPropertiesSet();
                listableBeanFactory.registerSingleton(name, stringRedisTemplate);
            });
        }
    }

    /**
     * 配置redisConnection
     *
     * @param dataSource
     */
    private RedisConnectionFactory getRedisConnection(RedisMessageProperties dataSource) {
        return getLettuceConnectionFactory(dataSource);
    }

    /**
     * 获取LettuceConnection
     *
     * @param dataSource
     */
    private RedisConnectionFactory getLettuceConnectionFactory(RedisMessageProperties dataSource) {
        RedisStandaloneConfiguration redisConfig = getRedisConfig(dataSource);
        RedisMessageProperties.Pool pool = dataSource.getLettuce().getPool();
        GenericObjectPoolConfig poolConfig = getPoolConfig(pool);
        LettucePoolingClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig).build();
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisConfig, clientConfiguration);
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }

    /**
     * 获取连接池配置
     *
     * @param pool
     */
    private GenericObjectPoolConfig getPoolConfig(RedisMessageProperties.Pool pool) {
        GenericObjectPoolConfig<?> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(pool.getMaxActive());
        poolConfig.setMaxIdle(pool.getMaxIdle());
        poolConfig.setMinIdle(pool.getMinIdle());
        if (pool.getTimeBetweenEvictionRuns() != null) {
            poolConfig.setTimeBetweenEvictionRunsMillis(pool.getTimeBetweenEvictionRuns().toMillis());
        }
        if (pool.getMaxWait() != null) {
            poolConfig.setMaxWaitMillis(pool.getMaxWait().toMillis());
        }
        return poolConfig;
    }

    /**
     * 获取redis连接配置
     *
     * @param dataSource
     */
    private RedisStandaloneConfiguration getRedisConfig(RedisMessageProperties dataSource) {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(dataSource.getHost());
        redisConfig.setPort(dataSource.getPort());
        redisConfig.setUsername(dataSource.getUsername());
        redisConfig.setPassword(RedisPassword.of(dataSource.getPassword()));
        redisConfig.setDatabase(dataSource.getDatabase());
        return redisConfig;
    }

}
