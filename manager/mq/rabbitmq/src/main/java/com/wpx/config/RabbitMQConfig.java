package com.wpx.config;

import com.wpx.property.RabbitMQC;
import com.wpx.property.RabbitMQProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig implements BeanFactoryAware {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    /**
     * appKittystar
     * 直连exchange
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        //durable 持久化
        //autoDelete exchange自动删除
        return new DirectExchange(RabbitMQC.EXCHANGE_NAME, true, false);
    }

    /**
     * 队列及其binding
     * 优先队列
     */
    @Bean("priorityQueue")
    public Queue queue() {
        return QueueBuilder.durable(RabbitMQC.QUEUE_NAME)
                .withArgument(RabbitMQC.MAX_PRIORITY, RabbitMQC.MAX_PRIORITY_SETTING)
                .build();
    }

    /**
     * 普通队列
     */
    @Bean("queue")
    public Queue kittystarBillQueue() {
        return QueueBuilder.durable(RabbitMQC.QUEUE_NAME).build();
    }

    @Bean
    public Binding binding() {
        return new Binding(
                RabbitMQC.QUEUE_NAME,
                Binding.DestinationType.QUEUE,
                RabbitMQC.EXCHANGE_NAME,
                RabbitMQC.ROUTING_KEY,
                null
        );
    }
}
