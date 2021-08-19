package com.wpx.model.kfsession.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.wpx.model.WechatResult;
import com.wpx.model.kfsession.MessageRecord;

import java.util.List;

/**
 * @author 不会飞的小鹏
 */
public class MessageRecordVO extends WechatResult {

    /**
     * 每次获取条数，最多10000条
     */
    private String number;

    /**
     * 消息id顺序从小到大，从1开始
     */
    @JSONField(name = "msgid")
    private String msgId;

    @JSONField(name = "recordlist")
    private List<MessageRecord> recordList;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public List<MessageRecord> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<MessageRecord> recordList) {
        this.recordList = recordList;
    }

    @Override
    public String toString() {
        return "MessageRecordVO{" +
                "number='" + number + '\'' +
                ", msgId='" + msgId + '\'' +
                ", recordList=" + recordList +
                '}';
    }

}
