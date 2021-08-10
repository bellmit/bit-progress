package com.wpx.manager.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: 不会飞的小鹏
 * @Description: RabbitMQ队列测试消费者
 */
@Component
public class TestConsumer {

    @RabbitListener(queues = "testqueue", containerFactory = "connection1ListenerContainer")
    @RabbitHandler
    public void Process(String s, Channel channel, Message message) throws IOException {
        System.out.println(s);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}