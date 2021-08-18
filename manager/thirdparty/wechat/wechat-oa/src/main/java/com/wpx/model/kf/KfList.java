package com.wpx.model.kf;

import com.alibaba.fastjson.annotation.JSONField;
import com.wpx.model.WechatResult;

import java.util.List;

/**
 * @author 不会飞的小鹏
 * @description： 客服列表
 */
public class KfList extends WechatResult {

    @JSONField(name = "kf_list")
    private List<Kf> kfList;

    public List<Kf> getKfList() {
        return kfList;
    }

    public void setKfList(List<Kf> kfList) {
        this.kfList = kfList;
    }

}
