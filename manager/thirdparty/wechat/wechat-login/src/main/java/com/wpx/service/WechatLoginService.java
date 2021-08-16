package com.wpx.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wpx.constant.NumberConstants;
import com.wpx.constant.StringConstants;
import com.wpx.constant.WechatLoginUrl;
import com.wpx.exception.CommonException;
import com.wpx.model.JsCode2SessionResult;
import com.wpx.okhttp.util.OkHttpClientUtils;
import com.wpx.util.StringUtils;
import com.wpx.util.WechatResultUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.wpx.constant.WechatConstant.*;
import static com.wpx.constant.WechatLoginConstants.*;

/**
 * @author 不会飞的小鹏
 * @description： 微信登录服务
 */
public class WechatLoginService {

    /**
     * 微信登录
     *
     * @param appId  微信下发的appId
     * @param appSecret  微信下发的secret
     * @param jsCode  临时登录凭证 code
     * @return JsCode2SessionResult
     */
    public JsCode2SessionResult jsCode2Session(String appId, String appSecret, String jsCode) throws IOException {
        Map<String, String> params = new HashMap<>(8);
        params.put(APP_ID, appId);
        params.put(APP_SECRET, appSecret);
        params.put(JS_CODE, jsCode);
        params.put(GRANT_TYPE, AUTHORIZATION_CODE);
        String result = OkHttpClientUtils.doGetWithParam(WechatLoginUrl.JS_CODE_TO_SESSION_URL, params);
        WechatResultUtils.wechatResultCheck(result);
        return JSON.parseObject(result, JsCode2SessionResult.class);
    }

}
