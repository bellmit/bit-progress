package com.wpx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wpx.constant.WechatPayConstants;
import com.wpx.constant.WechatPayUrl;
import com.wpx.okhttp.util.OkHttpClientUtils;
import okhttp3.MediaType;

import java.io.IOException;

import static com.wpx.constant.WechatPayConstants.MCH_ID;

/**
 * @author 不会飞的小鹏
 * @Description: 微信支付订单关闭服务
 */
public class WechatPayCloseService {

    /**
     * 关闭订单
     *
     * @param mchId
     * @param outTradeNo
     */
    public void closeOrder(String mchId, String outTradeNo) throws IOException {
        String closeUrl = WechatPayUrl.ORDER_CLOSE_URL;
        String url = closeUrl.replace(WechatPayConstants.OUT_TRADE_NO_PLACEHOLDER, outTradeNo);
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        JSONObject object = new JSONObject();
        object.put(MCH_ID, mchId);
        OkHttpClientUtils.doPost(url, JSON.toJSONString(object), mediaType);
    }

}
