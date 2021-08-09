package com.wpx.manager.rabbitmq.producer;

import com.wpx.constant.RabbitMQConstant;
import com.wpx.producer.BaseProducer;
import com.wpx.property.RabbitMQC;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author: 不会飞的小鹏
 * @Description: RabbitMQ测试生产者
 */
@Service
public class TestProducer extends BaseProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void test() {
        rabbitTemplate.convertAndSend("testexchange", "testroutingKey", "testaaaa", message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setHeader(RabbitMQConstant.RETRY_TIMES, 0);
            messageProperties.setPriority(RabbitMQC.MIN_PRIORITY_SETTING);
            return message;
        });
    }

}
