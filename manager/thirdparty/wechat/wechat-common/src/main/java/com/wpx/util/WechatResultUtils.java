package com.wpx.util;

import com.alibaba.fastjson.JSON;
import com.wpx.constant.NumberConstants;
import com.wpx.exception.CommonException;
import com.wpx.model.WechatResult;

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
    public static WechatResult wechatResultCheck(String result) {
        WechatResult wechatResult = JSON.parseObject(result, WechatResult.class);
        String errCode = wechatResult.getErrCode();
        if (!StringUtils.equals(NumberConstants.STRING_ZERO, errCode)) {
            throw new CommonException(Integer.parseInt(errCode), wechatResult.getErrMsg(), "微信接口返回异常");
        }
        return wechatResult;
    }

}
