package com.wpx.register;

import com.wpx.constant.RabbitMQConstant;
import com.wpx.property.BindingProperties;
import com.wpx.property.ExchangeProperties;
import com.wpx.property.QueueProperties;
import com.wpx.property.RabbitMQProperties;
import com.wpx.util.CollectionUtils;
import org.springframework.amqp.core.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: 不会飞的小鹏
 * @Description: RabbitMQ注册
 */
@Component
public class RabbitMQRegister implements BeanFactoryAware, InstantiationAwareBeanPostProcessor {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory) beanFactory;

        // 向IOC容器中注册交换机
        Map<String, ExchangeProperties> exchangeMessages = rabbitMQProperties.getExchange();
        if (CollectionUtils.nonEmpty(exchangeMessages)) {
            exchangeMessages.forEach((name, exchangeMessage) -> {
                String exchangeBeanName = name + RabbitMQConstant.EXCHANGE_BEAN_NAME;
                String exchangeName = exchangeMessage.getExchangeName();
                ExchangeProperties.ExchangeType exchangeType = exchangeMessage.getExchangeType();
                boolean durable = exchangeMessage.isDurable();
                boolean autoDelete = exchangeMessage.isAutoDelete();
                Map<String, Object> argument = exchangeMessage.getArgument();
                AbstractExchange exchange;
                switch (exchangeType) {
                    case TOPIC: {
                        exchange = new TopicExchange(exchangeName, durable, autoDelete, argument);
                        break;
                    }
                    case FANOUT: {
                        exchange = new FanoutExchange(exchangeName, durable, autoDelete, argument);
                        break;
                    }
                    case HEADERS: {
                        exchange = new HeadersExchange(exchangeName, durable, autoDelete, argument);
                        break;
                    }
                    default: {
                        exchange = new DirectExchange(exchangeName, durable, autoDelete, argument);
                        break;
                    }
                }
                listableBeanFactory.registerSingleton(exchangeBeanName, exchange);
            });
        }

        //  向IOC容器中注册队列
        Map<String, QueueProperties> queueMessages = rabbitMQProperties.getQueue();
        if (CollectionUtils.nonEmpty(queueMessages)) {
            queueMessages.forEach((name, queueMessage) -> {
                String queueBeanName = name + RabbitMQConstant.QUEUE_BEAN_NAME;
                String queueName = queueMessage.getQueueName();
                Map<String, Object> argument = queueMessage.getArgument();
                QueueBuilder builder = QueueBuilder.durable(queueName);
                if (CollectionUtils.nonEmpty(argument)) {
                    builder.withArguments(argument);
                }
                Queue queue = builder.build();
                listableBeanFactory.registerSingleton(queueBeanName, queue);
            });
        }

        //  向IOC容器中注册绑定
        Map<String, BindingProperties> bindingMessages = rabbitMQProperties.getBinding();
        if (CollectionUtils.nonEmpty(bindingMessages)) {
            bindingMessages.forEach((name, bindingMessage) -> {
                String bindingBeanName = name + RabbitMQConstant.BINDING_BEAN_NAME;
                String destinationName = bindingMessage.getDestinationName();
                BindingProperties.DestinationType bindingDestinationType = bindingMessage.getDestinationType();
                String exchangeName = bindingMessage.getExchangeName();
                String routingKey = bindingMessage.getRoutingKey();
                Map<String, Object> argument = bindingMessage.getArgument();
                Binding.DestinationType destinationType;
                switch (bindingDestinationType) {
                    case EXCHANGE: {
                        destinationType = Binding.DestinationType.EXCHANGE;
                        break;
                    }
                    default: {
                        destinationType = Binding.DestinationType.QUEUE;
                        break;
                    }
                }
                Binding binding = new Binding(destinationName, destinationType, exchangeName, routingKey, argument);
                listableBeanFactory.registerSingleton(bindingBeanName, binding);
            });
        }

    }

}
