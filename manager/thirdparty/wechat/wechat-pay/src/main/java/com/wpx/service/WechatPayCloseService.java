package com.wpx.service;

import com.wpx.util.JsonUtils;
import com.wpx.constant.WechatPayConstants;
import com.wpx.constant.WechatPayUrl;
import com.wpx.okhttp.util.OkHttpClientUtils;
import com.wpx.util.WechatRequestUtils;
import okhttp3.MediaType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.wpx.constant.WechatPayConstants.MCH_ID;

/**
 * @author 不会飞的小鹏
 * 微信支付订单关闭服务
 */
public class WechatPayCloseService {

    /**
     * 关闭订单
     *
     * @param mchId
     * @param outTradeNo
     */
    public void closeOrder(String mchId, String outTradeNo) {
        String closeUrl = WechatPayUrl.ORDER_CLOSE_URL;
        String url = closeUrl.replace(WechatPayConstants.OUT_TRADE_NO_PLACEHOLDER, outTradeNo);
        Map<String, String> params = new HashMap<>(4);
        params.put(MCH_ID, mchId);
        WechatRequestUtils.doPost(url, JsonUtils.serializeObject(params));
    }

}
