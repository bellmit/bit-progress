package com.wpx.util;

import com.alibaba.fastjson.JSON;
import com.wpx.constant.NumberConstants;
import com.wpx.exception.CommonException;
import com.wpx.model.WechatResult;

import java.util.Objects;

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
        return wechatResultCheck(result, WechatResult.class);
    }

    /**
     * 检查微信接口调用结果
     *
     * @param result
     */
    public static <T extends WechatResult> T wechatResultCheck(String result, Class<T> target) {
        T data = JSON.parseObject(result, target);
        String errCode = data.getErrCode();
        if (Objects.nonNull(errCode) && !StringUtils.equals(NumberConstants.STRING_ZERO, errCode)) {
            throw new CommonException(Integer.parseInt(errCode), data.getErrMsg(), "微信接口返回异常");
        }
        return data;
    }

}
