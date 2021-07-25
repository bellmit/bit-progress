package com.wpx.rabbitmq.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.wpx.constant.RabbitMQConstant;
import com.wpx.constant.RedisKeyPrefix;
import com.wpx.rabbitmq.property.RabbitMQProperties;
import com.wpx.okhttp.util.RedisCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public abstract class BaseConsumer {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    /**
     * 消息幂等性
     * 从单一key改为动态定时key
     * 不管消息是否重复消费都进行确认，如果消费失败则进行重发
     *
     * @param channel
     * @param message
     */
    public Boolean consumedIdentifier(Channel channel, Message message) throws IOException {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        Object day = headers.get(RabbitMQConstant.DAY);
        String messageIdentifier = String.valueOf(headers.get(RabbitMQConstant.IDENTIFIER));
        log.info("consumedIdentifier examine [{}]", messageIdentifier);
        String consumedIdentifierKey = RedisKeyPrefix.rabbitMQConsumedIdentifier(String.valueOf(day));
        String identifier = redisCacheUtils.getForHash(consumedIdentifierKey, messageIdentifier);
        if (StringUtils.isEmpty(identifier)) {
            log.info("consumeMessage start message [{}]", message);
            redisCacheUtils.putForHash(consumedIdentifierKey, messageIdentifier, messageIdentifier);
            redisCacheUtils.expire(consumedIdentifierKey);
            return Boolean.TRUE;
        } else {
            log.info("consumeMessage is repeat message [{}]", message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            return Boolean.FALSE;
        }
    }

    /**
     * 重试发送消息
     *
     * @param t
     * @param channel
     * @param message
     */
    public <T> void retryAndSendMessage(T t, Channel channel, Message message) {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();

        int retryTimes = (int) headers.get(RabbitMQConstant.RETRY_TIMES);
        int maxRetryTimes = (int) headers.get(RabbitMQConstant.MAX_RETRY_TIMES);
        retryTimes = retryTimes + 1;

        // 从redis hash中删除消费标识码
        String day = String.valueOf(headers.get(RabbitMQConstant.DAY));
        String identifier = String.valueOf(headers.get(RabbitMQConstant.IDENTIFIER));
        String consumedIdentifierKey = RedisKeyPrefix.rabbitMQConsumedIdentifier(day);
        redisCacheUtils.deleteForHash(consumedIdentifierKey, identifier);
        log.info("consumedIdentifier is deleteForRedis");

        if (retryTimes > maxRetryTimes) {
            log.error("retryAndSendMessage error obj:[{}]", t);
            return;
        }
        log.info("retryAndSendMessage");
        message.getMessageProperties().setHeader(RabbitMQConstant.RETRY_TIMES, retryTimes);
        MessageProperties messageProperties = message.getMessageProperties();
        String queueName = messageProperties.getConsumerQueue();
        String routingKey = messageProperties.getReceivedRoutingKey();
        String exchangeName = messageProperties.getReceivedExchange();

        AMQP.BasicProperties build = new AMQP.BasicProperties().builder()
                .headers(message.getMessageProperties().getHeaders())
                .appId(messageProperties.getAppId())
                .clusterId(messageProperties.getClusterId())
                .contentEncoding(messageProperties.getContentEncoding())
                .contentType(messageProperties.getContentType())
                .correlationId(messageProperties.getCorrelationId())
                //优先级最高，Larger numbers indicate higher priority
                .priority(RabbitMQProperties.MAX_PRIORITY_SETTING)
                .build();
        try {
            channel.basicPublish(exchangeName, routingKey, build, message.getBody());
        } catch (IOException e) {
            log.error("retryAndSendMessage error queueName:[{}] obj:[{}]", queueName, t);
            log.error("retryAndSendMessage error ", e);
        }
    }

}
