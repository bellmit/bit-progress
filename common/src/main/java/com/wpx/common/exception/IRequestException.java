package com.wpx.common.exception;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/26 20:03
 * @Description 请求异常信息接口
 */
public interface IRequestException {

    /**
     * 获取异常状态码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();

}
