package com.wpx.model.kfsession.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wpx.model.WechatResult;
import com.wpx.model.kfsession.KfSession;

import java.util.List;

/**
 * @author 不会飞的小鹏
 *  客服会话列表
 */
public class KfSessionVO extends WechatResult {

    @JsonProperty("sessionlist")
    private List<KfSession> sessionList;

    public List<KfSession> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<KfSession> sessionList) {
        this.sessionList = sessionList;
    }

    @Override
    public String toString() {
        return "KfSessionVO{" +
                "sessionList=" + sessionList +
                '}';
    }

}
