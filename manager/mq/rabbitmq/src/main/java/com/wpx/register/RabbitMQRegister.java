package com.wpx.register;

import com.wpx.constant.RabbitMQConstant;
import com.wpx.property.QueueProperties;
import com.wpx.property.RabbitMQProperties;
import com.wpx.util.CollectionUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Map;

public class RabbitMQRegister implements BeanFactoryAware {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        Map<String, QueueProperties> queueMessages = rabbitMQProperties.getQueue();
        if (CollectionUtils.nonEmpty(queueMessages)) {
            queueMessages.forEach((name, queueMessage) -> {
                String exchangeBeanName = name + RabbitMQConstant.EXCHANGE_BEAN_NAME;
                String queueBeanName = name + RabbitMQConstant.QUEUE_BEAN_NAME;
                String bindingBeanName = name + RabbitMQConstant.BINDING_BEAN_NAME;
                // 需要检查exchange bean是否存在
                String exchangeName = queueMessage.getExchangeName();
                String queueName = queueMessage.getQueueName();
                String routingKey = queueMessage.getRoutingKey();
                Map<String, Object> exchangeArgument = queueMessage.getExchangeArgument();
                Map<String, Object> queueArgument = queueMessage.getQueueArgument();
                Map<String, Object> bindingArgument = queueMessage.getBindingArgument();

                // 装载DirectExchange
                DirectExchange exchange = new DirectExchange(exchangeName, true, false, exchangeArgument);
                listableBeanFactory.registerSingleton(exchangeBeanName, exchange);

                // 装载Queue
                QueueBuilder builder = QueueBuilder.durable(queueName);
                if (CollectionUtils.nonEmpty(queueArgument)) {
                    builder.withArguments(queueArgument);
                }
                Queue queue = builder.build();
                listableBeanFactory.registerSingleton(queueBeanName, queue);

                // 装载Binding
                Binding.DestinationType destinationType = Binding.DestinationType.QUEUE;
                Binding binding = new Binding(queueName, destinationType, exchangeName, routingKey, bindingArgument);
                listableBeanFactory.registerSingleton(bindingBeanName, binding);
            });
        }
    }

}
