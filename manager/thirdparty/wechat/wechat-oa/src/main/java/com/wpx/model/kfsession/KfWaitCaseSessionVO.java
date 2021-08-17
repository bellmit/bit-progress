package com.wpx.model.kfsession;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author 不会飞的小鹏
 * @description： 未接入客服会话列表
 */
public class KfWaitCaseSessionVO {

    /**
     * 未接入会话数量
     */
    private Integer count;

    /**
     * 未接入会话列表，最多返回100条数据，按照来访顺序
     */
    @JSONField(name = "waitcaselist")
    private List<KfWaitCaseSession> waitCaseList;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<KfWaitCaseSession> getWaitCaseList() {
        return waitCaseList;
    }

    public void setWaitCaseList(List<KfWaitCaseSession> waitCaseList) {
        this.waitCaseList = waitCaseList;
    }

}
