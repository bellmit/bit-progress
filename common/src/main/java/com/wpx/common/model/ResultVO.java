package com.wpx.common.model;

import com.wpx.common.constant.ResultConstant;
import com.wpx.common.exception.CustomizeException;
import com.wpx.common.exception.ExceptionMessage;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/13 17:26
 * @Description 返回请求结果
 */
public class ResultVO<T> {

    /**
     * 请求返回的状态码
     */
    private Integer code;

    /**
     * 请求返回的message
     */
    private String message;

    /**
     * 请求返回的数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultVO(Integer code) {
        this(code, null);
    }

    public ResultVO(Integer code, String message) {
        this(code, message, null);
    }

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 请求成功，无返回信息
     */
    public static ResultVO success() {
        return success(ResultConstant.SUCCESS_MESSAGE);
    }

    /**
     * 请求成功，返回特定message
     * 
     * @param message
     */
    public static ResultVO success(String message) {
        return success(ResultConstant.SUCCESS_CODE, message, null);
    }

    /**
     * 请求成功，返回特定data
     *
     * @param data
     */
    public static <T> ResultVO<T> success(T data) {
        return success(ResultConstant.SUCCESS_CODE, ResultConstant.SUCCESS_MESSAGE, data);
    }

    /**
     * 请求成功，返回特定message和data
     * 
     * @param message
     * @param data
     */
    public static <T> ResultVO<T> success(String message, T data) {
        return success(ResultConstant.SUCCESS_CODE, message, data);
    }

    /**
     * 请求成功，返回特定code、message和data
     *
     * @param code
     * @param message
     * @param data
     */
    public static <T> ResultVO<T> success(Integer code, String message, T data) {
        return new ResultVO(code, message, data);
    }

    /**
     * 请求失败，无返回信息
     */
    public static ResultVO errorOf() {
        return errorOf(ResultConstant.FAIL_CODE);
    }

    /**
     * 请求失败，返回特定code
     * 
     * @param code
     */
    public static ResultVO errorOf(Integer code) {
        return errorOf(code, ResultConstant.FAIL_MESSAGE);
    }

    /**
     * 请求失败，返回特定code
     *
     * @param message
     */
    public static ResultVO errorOf(String message) {
        return errorOf(ResultConstant.FAIL_CODE, message);
    }

    /**
     * 请求失败，返回特定code, message
     *
     * @param code
     * @param message
     */
    public static ResultVO errorOf(Integer code, String message) {
        return new ResultVO(code, message);
    }

    /**
     * 抛出异常
     *
     * @param exception
     */
    public static ResultVO errorOf(ExceptionMessage exception) {
        return errorOf(exception.getCode(), exception.getMessage());
    }

    /**
     * 抛出异常
     *
     * @param exception
     */
    public static ResultVO errorOf(CustomizeException exception) {
        return errorOf(exception.getCode(), exception.getMessage());
    }

}
