package com.wpx.model.kf.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 * @description： 卡券消息
 */
public class WxCard implements KfBaseMessage {

    /**
     * 卡券ID
     */
    @JSONField(name = "card_id")
    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}
