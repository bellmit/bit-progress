package com.wpx.model.kfsession;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author 不会飞的小鹏
 */
public class MessageRecord {

    /**
     * 用户的openid
     */
    @JSONField(name = "openid")
    private String openId;

    /**
     * 操作ID（会话状态）
     */
    @JSONField(name = "opercode")
    private String operCode;

    /**
     * 聊天记录
     */
    @JSONField(name = "text")
    private String text;

    /**
     * 操作时间，UNIX时间戳
     */
    private LocalDateTime time;

    /**
     * 客服账号
     */
    private String worker;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @JSONField(name = "time")
    public void setTime(Integer time) {
        this.time = LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.ofHours(8));
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

}