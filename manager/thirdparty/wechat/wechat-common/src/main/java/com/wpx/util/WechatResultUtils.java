package com.wpx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wpx.constant.NumberConstants;

import static com.wpx.constant.WechatConstant.*;

/**
 * @author 不会飞的小鹏
 * @description： 微信接口调用结果处理
 */
public class WechatResultUtils {

    /**
     * 检查微信接口调用结果
     *
     * @param result
     */
    public static void wechatResultCheck(String result) {
        JSONObject resultObject = JSON.parseObject(result);
        String errCode = resultObject.getString(ERR_CODE);
        if (StringUtils.equals(NumberConstants.STRING_ZERO, errCode)) {
            String errMsg = resultObject.getString(ERR_MSG);
            throw new RuntimeException(errMsg);
        }
    }

}
