package com.wpx.rabbitmq.config;

import com.wpx.rabbitmq.property.RabbitMQProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /*
        exchange
     */

    /**
     * appKittystar
     * 直连exchange
     *
     * @return
     */
    @Bean
    public DirectExchange appKittystarDirect() {
        //durable 持久化
        //autoDelete exchange自动删除
        return new DirectExchange(RabbitMQProperties.EXCHANGE_NAME, true, false);
    }

    /**
     * 队列及其binding
     * 优先队列
     */
    @Bean("priorityQueue")
    public Queue queue() {
        return QueueBuilder.durable(RabbitMQProperties.QUEUE_NAME)
                .withArgument(RabbitMQProperties.MAX_PRIORITY, RabbitMQProperties.MAX_PRIORITY_SETTING)
                .build();
    }

    /**
     * 普通队列
     */
    @Bean("queue")
    public Queue kittystarBillQueue() {
        return QueueBuilder.durable(RabbitMQProperties.QUEUE_NAME).build();
    }

    @Bean
    public Binding binding() {
        return new Binding(
                RabbitMQProperties.QUEUE_NAME,
                Binding.DestinationType.QUEUE,
                RabbitMQProperties.EXCHANGE_NAME,
                RabbitMQProperties.ROUTING_KEY,
                null
        );
    }

}
