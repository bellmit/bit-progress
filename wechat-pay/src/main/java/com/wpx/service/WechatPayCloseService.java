package com.wpx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wpx.constant.WechatPayConstants;
import com.wpx.constant.WechatUrl;
import com.wpx.util.HttpClientUtils;
import okhttp3.MediaType;

import java.io.IOException;

/**
 * @author 不会飞的小鹏
 * @Description: 微信支付订单关闭服务
 */
public class WechatPayCloseService {

    /**
     * 关闭订单
     *
     * @param mchid
     * @param outTradeNo
     */
    public void closeOrder(String mchid, String outTradeNo) {
        String closeUrl = WechatUrl.ORDER_CLOSE_URL;
        String url = closeUrl.replace(WechatPayConstants.OUT_TRADE_NO_PLACEHOLDER, outTradeNo);
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        JSONObject object = new JSONObject();
        object.put("mchid", mchid);
        try {
            HttpClientUtils.doPost(url, JSON.toJSONString(object), mediaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
