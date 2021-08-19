package com.wpx.model.menu.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 */
public class MenuQueryDTO {

    /**
     * user_id可以是粉丝的OpenID，也可以是粉丝的微信号
     */
    @JSONField(name = "user_id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MenuQueryDTO{" +
                "userId='" + userId + '\'' +
                '}';
    }

}
