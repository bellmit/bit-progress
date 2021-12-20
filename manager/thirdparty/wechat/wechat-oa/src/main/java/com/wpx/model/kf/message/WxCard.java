package com.wpx.model.kf.message;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 不会飞的小鹏
 *  卡券消息
 */
public class WxCard implements KfBaseMessage {

    /**
     * 卡券ID
     */
    @JsonProperty("card_id")
    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}
