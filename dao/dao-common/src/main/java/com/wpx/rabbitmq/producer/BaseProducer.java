package com.wpx.rabbitmq.producer;//package com.wpx.rabbitmq.producer;
//
//import com.wpx.constant.RabbitMQConstant;
//import com.wpx.constant.RedisKeyPrefix;
//import com.wpx.rabbitmq.config.RabbitMQDefaultConfig;
//import com.wpx.rabbitmq.property.RabbitMQProperties;
//import com.wpx.util.RedisCacheUtil;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//public abstract class BaseProducer {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    private RabbitMQDefaultConfig rabbitMQDefaultConfig;
//
//    @Autowired
//    private RedisCacheUtil redisCacheUtil;
//
//    /**
//     * 创建并发送消息，使用默认的重试次数
//     *
//     * @param t
//     * @param exchangeName
//     * @param routingKey
//     * @param <T>
//     */
//    public <T> void createAndSendMessage(T t, String exchangeName, String routingKey) {
//        createAndSendMessage(t, exchangeName, routingKey, rabbitMQDefaultConfig.getMaxRetryTimes());
//    }
//
//    /**
//     * 创建并发送消息
//     *
//     * @param t
//     * @param exchangeName
//     * @param routingKey
//     * @param maxRetryTimes
//     * @param <T>
//     */
//    public <T> void createAndSendMessage(T t, String exchangeName, String routingKey, Integer maxRetryTimes) {
//        int day = LocalDateTime.now().getDayOfYear();
//        Integer maxRetryTimesConfig = null == maxRetryTimes ? rabbitMQDefaultConfig.getMaxRetryTimes() : maxRetryTimes;
//        String identifierKey = RedisKeyPrefix.rabbitMQIdentifier(day);
//        Long identifier = redisCacheUtil.incrementForValue(identifierKey, 1L);
//        redisCacheUtil.expire(identifierKey);
//
//        rabbitTemplate.convertAndSend(exchangeName, routingKey, t, message -> {
//                    MessageProperties messageProperties = message.getMessageProperties();
//                    messageProperties.setHeader(RabbitMQConstant.RETRY_TIMES, 0);
//                    messageProperties.setHeader(RabbitMQConstant.MAX_RETRY_TIMES, maxRetryTimesConfig);
//                    messageProperties.setHeader(RabbitMQConstant.DAY, day);
//                    messageProperties.setHeader(RabbitMQConstant.IDENTIFIER, identifier);
//                    messageProperties.setPriority(RabbitMQProperties.MIN_PRIORITY_SETTING);
//                    return message;
//                }
//        );
//    }
//
//}
