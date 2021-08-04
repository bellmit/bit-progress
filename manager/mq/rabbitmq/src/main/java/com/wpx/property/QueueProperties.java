package com.wpx.property;

import java.util.Map;

public class QueueProperties {

    /**
     * 交换机名称
     */
    private String exchangeName;

    /**
     * 队列名称
     */
    private String queueName;

    /**
     * RoutingKey
     */
    private String routingKey;

    /**
     * 交换机参数
     */
    private Map<String, Object> exchangeArgument;

    /**
     * 队列参数
     */
    private Map<String, Object> queueArgument;

    /**
     * binding参数
     */
    private Map<String, Object> bindingArgument;

    /**
     * 重试次数
     */
    private Integer maxRetryTimes;

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public Map<String, Object> getQueueArgument() {
        return queueArgument;
    }

    public void setArgument(Map<String, Object> queueArgument) {
        this.queueArgument = queueArgument;
    }

    public Map<String, Object> getExchangeArgument() {
        return exchangeArgument;
    }

    public void setExchangeArgument(Map<String, Object> exchangeArgument) {
        this.exchangeArgument = exchangeArgument;
    }

    public Map<String, Object> getBindingArgument() {
        return bindingArgument;
    }

    public void setBindingArgument(Map<String, Object> bindingArgument) {
        this.bindingArgument = bindingArgument;
    }

    public Integer getMaxRetryTimes() {
        return maxRetryTimes;
    }

    public void setMaxRetryTimes(Integer maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

}
