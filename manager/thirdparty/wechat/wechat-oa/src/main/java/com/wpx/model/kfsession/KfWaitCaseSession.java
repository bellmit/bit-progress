package com.wpx.model.kfsession;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 * @description： 未接入会话
 */
public class KfWaitCaseSession {

    /**
     * 最后一条消息的时间
     */
    @JSONField(name = "latest_time")
    private String latestTime;

    /**
     * 用户openid
     */
    @JSONField(name = "openid")
    private String openId;

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
