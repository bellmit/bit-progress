package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/26 20:03
 * 请求异常信息接口
 */
public interface IExceptionMessage {

    /**
     * 获取异常状态码
     *
     * @return 状态码
     */
    Integer getCode();

    /**
     * 获取错误码
     *
     * @return： 错误码
     */
    String getError();

    /**
     * 获取异常信息
     *
     * @return： 异常信息
     */
    String getMessage();

}
