package com.wpx.register;

import com.wpx.property.RedisDataSourceProperties;
import com.wpx.property.RedisMessageProperties;
import com.wpx.util.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
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
        Map<String, RedisMessageProperties> redisDataSource = redisDataSourceProperties.getRedisDataSource();
        if (CollectionUtils.nonEmpty(redisDataSource)) {
            redisDataSource.forEach((name, dataSource) -> {
                StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
                RedisConnectionFactory redisConnection = getRedisConnection(dataSource);
                stringRedisTemplate.setConnectionFactory(redisConnection);
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
        RedisMessageProperties.ClientType clientType = dataSource.getClientType();
        RedisStandaloneConfiguration redisConfig = getRedisConfig(dataSource);
        switch (clientType) {
            case JEDIS: {
                return getJedisConnectionFactory(redisConfig);
            }
            default: {
                return getLettuceConnectionFactory(redisConfig);
            }
        }
    }

    /**
     * 获取LettuceConnection
     *
     * @param redisConfig
     */
    private RedisConnectionFactory getLettuceConnectionFactory(RedisStandaloneConfiguration redisConfig) {
        return new LettuceConnectionFactory(redisConfig);
    }

    /**
     * 获取JedisConnectionFactory
     *
     * @param redisConfig
     */
    public JedisConnectionFactory getJedisConnectionFactory(RedisStandaloneConfiguration redisConfig) {
        return new JedisConnectionFactory(redisConfig);
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
