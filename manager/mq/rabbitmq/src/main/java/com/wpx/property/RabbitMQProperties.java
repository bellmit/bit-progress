package com.wpx.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Author: 不会飞的小鹏
 * @Description: RabbitMQ配置
 */
@Configuration
@ConfigurationProperties(prefix = RabbitMQProperties.PREFIX)
public class RabbitMQProperties {

    public static final String PREFIX = "spring.rabbitmq";

    /**
     * 交换机配置
     */
    private Map<String, ExchangeProperties> exchange;

    /**
     * 队列配置
     */
    private Map<String, QueueProperties> queue;

    /**
     * 绑定配置
     */
    private Map<String, BindingProperties> binding;

    public Map<String, ExchangeProperties> getExchange() {
        return exchange;
    }

    public void setExchange(Map<String, ExchangeProperties> exchange) {
        this.exchange = exchange;
    }

    public Map<String, QueueProperties> getQueue() {
        return queue;
    }

    public void setQueue(Map<String, QueueProperties> queue) {
        this.queue = queue;
    }

    public Map<String, BindingProperties> getBinding() {
        return binding;
    }

    public void setBinding(Map<String, BindingProperties> binding) {
        this.binding = binding;
    }

}
