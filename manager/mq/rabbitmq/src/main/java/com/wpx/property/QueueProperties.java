package com.wpx.property;

import java.util.Map;

/**
 * @Author: 不会飞的小鹏
 * @Description: 队列配置
 */
public class QueueProperties {

    /**
     * 队列名称
     */
    private String queueName;

    /**
     * 交换机参数
     */
    private Map<String, Object> argument;

    /**
     * 重试次数
     */
    private Integer maxRetryTimes;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Map<String, Object> getArgument() {
        return argument;
    }

    public void setArgument(Map<String, Object> argument) {
        this.argument = argument;
    }

    public Integer getMaxRetryTimes() {
        return maxRetryTimes;
    }

    public void setMaxRetryTimes(Integer maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

}
