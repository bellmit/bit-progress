package com.wpx.model;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 用于返回查询结果
 */
public class BooleanVO {

    private Boolean result;

    public BooleanVO(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public static BooleanVO result(Boolean result) {
        return new BooleanVO(result);
    }

    @Override
    public String toString() {
        return "BooleanVO{" +
                "result=" + result +
                '}';
    }
}
