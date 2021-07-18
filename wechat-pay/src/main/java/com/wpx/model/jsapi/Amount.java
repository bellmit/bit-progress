package com.wpx.model.jsapi;

/**
 * @author 不会飞的小鹏
 * create on 2021/7/19 0:12
 * @Description: 订单金额信息
 */
public class Amount {

    /**
     * 必填
     * 订单总金额，单位为分
     */
    private Integer total;

    /**
     * 非必填
     * 货币类型，CNY：人民币，境内商户号仅支持人民币
     * 默认人民币
     * string[1,16]
     */
    private String currency;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Amount(Integer total, String currency) {
        this.total = total;
        this.currency = currency;
    }

}
