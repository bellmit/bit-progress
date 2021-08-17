package com.wpx.model.kfsession;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 * @description： 客服会话
 */
public class KfSessionDTO {

    /**
     * 客服账号
     */
    @JSONField(name = "kf_account")
    private String kfAccount;

    /**
     * 用户openId
     */
    @JSONField(name = "openid")
    private String openId;

    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
