package com.wpx.model.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wpx.model.WechatResult;

import java.util.List;

/**
 * @author 不会飞的小鹏
 *  客服列表
 */
public class KfList extends WechatResult {

    @JsonProperty("kf_list")
    private List<Kf> kfList;

    public List<Kf> getKfList() {
        return kfList;
    }

    public void setKfList(List<Kf> kfList) {
        this.kfList = kfList;
    }

}
