package com.wpx.processor;

import com.wpx.constant.WechatUrl;
import com.wpx.model.AccessToken;
import com.wpx.okhttp.util.OkHttpClientUtils;
import com.wpx.util.WechatRequestUtils;
import com.wpx.util.WechatResultUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.wpx.constant.WechatConstant.*;

/**
 * @author 不会飞的小鹏
 * @description： 微信应用处理器
 */
public class WechatProcessor {

    /**
     * 获取微信应用接口调用凭证
     *
     * @param appId
     * @param appSecret
     */
    public static AccessToken getAccessToken(String appId, String appSecret) {
        Map<String, String> params = new HashMap<>(8);
        params.put(GRANT_TYPE, ACCESS_TOKEN_CLIENT_CREDENTIAL);
        params.put(APP_ID, appId);
        params.put(APP_SECRET, appSecret);
        String result = WechatRequestUtils.doGet(WechatUrl.ACCESS_TOKEN_URL, params);
        return WechatResultUtils.wechatResultCheck(result, AccessToken.class);
    }

}
