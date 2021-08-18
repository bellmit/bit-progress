package com.wpx.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 * @description： 微信登录信息结果
 */
public class JsCode2SessionResult extends WechatResult {

    /**
     * openId
     */
    @JSONField(name = "openid")
    private String openId;

    /**
     * unionId(符合条件才会下发)
     */
    @JSONField(name = "unionid")
    private String unionId;

    /**
     * sessionKey
     */
    @JSONField(name = "session_key")
    private String sessionKey;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

}
