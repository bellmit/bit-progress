package com.wpx.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = RabbitMQProperties.PREFIX)
public class RabbitMQProperties {

    public static final String PREFIX = "spring.rabbit";

    private Map<String, QueueProperties> queue;

    public Map<String, QueueProperties> getQueue() {
        return queue;
    }

    public void setQueue(Map<String, QueueProperties> queue) {
        this.queue = queue;
    }

}
