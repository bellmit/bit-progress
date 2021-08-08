package com.wpx.exception;

import static com.wpx.exception.BaseErrorCodes.*;
import static com.wpx.exception.BaseMessageCodes.*;

/**
 * @Author: 不会飞的小鹏
 * @Description: 基础模块错误枚举
 */
public enum BaseExceptionMessage implements IExceptionMessage {
    
    /**
     * 非超管只能编辑自己的信息
     */
    NOT_ROOT_EDIT_OTHER_INFO_EXCEPTION(2006, NOT_ROOT_EDIT_OTHER_INFO_CODE, NOT_ROOT_EDIT_OTHER_INFO_MESSAGE),
    /**
     * 非超管只能查看自己的信息
     */
    NOT_ROOT_CHECK_OTHER_INFO_EXCEPTION(2007, NOT_ROOT_CHECK_OTHER_INFO_CODE, NOT_ROOT_CHECK_OTHER_INFO_MESSAGE),
    /**
     * 不允许添加wpx之外的超管
     */
    ALLOW_ADD_WPX_EXCEPT_ROOT_EXCEPTION(2008, ALLOW_ADD_WPX_EXCEPT_ROOT_CODE, ALLOW_ADD_WPX_EXCEPT_ROOT_MESSAGE),
    /**
     * 超管不可删除
     */
    ROOT_DELETE_EXCEPTION(2009, ROOT_DELETE_EXCEPTION_CODE, ROOT_DELETE_EXCEPTION_MESSAGE_MESSAGE),
    /**
     * 非超管不可删除账号
     */
    NOT_ROOT_DELETE_ACCOUNT_EXCEPTION(2010, NOT_ROOT_CANNOT_DELETE_CODE, NOT_ROOT_CANNOT_DELETE_MESSAGE),
    /**
     * 超管不可禁用
     */
    ROOT_DISABLED_EXCEPTION(2011, ROOT_CANNOT_DISABLED_CODE, ROOT_CANNOT_DISABLED_MESSAGE),
    /**
     * 非超管不可禁用账号
     */
    NOT_ROOT_DISABLED_EXCEPTION(2012, NOT_ROOT_CANNOT_DISABLED_CODE, NOT_ROOT_CANNOT_DISABLED_MESSAGE),
    /**
     * 不能给同级或上级设权限
     */
    ALLOW_SUPERIOR_ROLE_EXCEPTION(2013, NOT_ALLOW_SUPERIOR_ROLE_CODE, NOT_ALLOW_SUPERIOR_ROLE_MESSAGE),
    /**
     * 管理员记录不存在
     */
    MANAGER_NOT_EXIST_EXCEPTION(2014, MANAGER_NOT_EXIST_CODE, MANAGER_NOT_EXIST_EXCEPTION_MESSAGE),
    /**
     * 新增管理员失败
     */
    MANAGER_SAVE_EXCEPTION(2015, MANAGER_SAVE_EXCEPTION_CODE, MANAGER_SAVE_EXCEPTION_MESSAGE),
    /**
     * 更新管理员信息失败
     */
    MANAGER_UPDATE_EXCEPTION(2016, MANAGER_UPDATE_EXCEPTION_CODE, MANAGER_UPDATE_EXCEPTION_MESSAGE),
    /**
     * 管理员记录删除失败
     */
    MANAGER_DELETE_EXCEPTION(2017, MANAGER_DELETE_EXCEPTION_CODE, MANAGER_DELETE_EXCEPTION_MESSAGE),
    /**
     * 应用信息不存在
     */
    APPLICATION_NOT_EXIST_EXCEPTION(3001, APPLICATION_NOT_EXIST_EXCEPTION_CODE, APPLICATION_NOT_EXIST),
    /**
     * 应用信息保存失败
     */
    APPLICATION_SAVE_EXCEPTION(3002, APPLICATION_SAVE_EXCEPTION_CODE, APPLICATION_SAVE_EXCEPTION_MESSAGE),
    /**
     * 应用信息更新失败
     */
    APPLICATION_UPDATE_EXCEPTION(3003, APPLICATION_UPDATE_EXCEPTION_CODE, APPLICATION_UPDATE_EXCEPTION_MESSAGE),
    /**
     * 应用信息删除失败
     */
    APPLICATION_DELETE_EXCEPTION(3004, APPLICATION_DELETE_EXCEPTION_CODE, APPLICATION_DELETE_EXCEPTION_MESSAGE),
    /**
     * 应用配置信息不存在
     */
    APPLICATIONINFO_NOT_EXIST_EXCEPTION(3005, APPLICATIONINFO_NOT_EXIST_EXCEPTION_CODE, APPLICATIONINFO_NOT_EXIST),
    /**
     * 应用配置信息保存失败
     */
    APPLICATIONINFO_SAVE_EXCEPTION(3006, APPLICATIONINFO_SAVE_EXCEPTION_CODE, APPLICATIONINFO_SAVE_EXCEPTION_MESSAGE),
    /**
     * 应用配置信息更新失败
     */
    APPLICATIONINFO_UPDATE_EXCEPTION(3007, APPLICATIONINFO_UPDATE_EXCEPTION_CODE, APPLICATIONINFO_UPDATE_EXCEPTION_MESSAGE),
    /**
     * 应用配置信息删除失败
     */
    APPLICATIONINFO_DELETE_EXCEPTION(3008, APPLICATIONINFO_DELETE_EXCEPTION_CODE, APPLICATIONINFO_DELETE_EXCEPTION_MESSAGE),
    /**
     * 应用主题不存在
     */
    APPLICATIONTOPIC_NOT_EXIST_EXCEPTION(3009, APPLICATIONTOPIC_NOT_EXIST_EXCEPTION_CODE, APPLICATIONTOPIC_NOT_EXIST),
    /**
     * 应用主题保存失败
     */
    APPLICATIONTOPIC_SAVE_EXCEPTION(3010, APPLICATIONTOPIC_SAVE_EXCEPTION_CODE, APPLICATIONTOPIC_SAVE_EXCEPTION_MESSAGE),
    /**
     * 应用主题更新失败
     */
    APPLICATIONTOPIC_UPDATE_EXCEPTION(3011, APPLICATIONTOPIC_UPDATE_EXCEPTION_CODE, APPLICATIONTOPIC_UPDATE_EXCEPTION_MESSAGE),
    /**
     * 应用主题删除失败
     */
    APPLICATIONTOPIC_DELETE_EXCEPTION(3012, APPLICATIONTOPIC_DELETE_EXCEPTION_CODE, APPLICATIONTOPIC_DELETE_EXCEPTION_MESSAGE),

    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 错误码
     */
    private String error;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 获取异常状态码
     */
    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getError() {
        return this.error;
    }

    /**
     * 获取异常信息
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    BaseExceptionMessage(Integer code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

}
