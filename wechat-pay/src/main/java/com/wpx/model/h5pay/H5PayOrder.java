package com.wpx.model.h5pay;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 * create on 2021/7/19 1:08
 * @Description APP支付 发起支付
 */
public class H5PayOrder {

    /**
     * h5_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付，h5_url的有效期为5分钟
     */
    @JSONField(name = "h5_url")
    private String h5Url;

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }

}
