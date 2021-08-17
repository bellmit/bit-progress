package com.wpx.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 */
public class WechatResult {

    /**
     * 状态码
     */
    @JSONField(name = "errcode")
    private String errCode;

    /**
     * 异常信息
     */
    @JSONField(name = "errmsg")
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
