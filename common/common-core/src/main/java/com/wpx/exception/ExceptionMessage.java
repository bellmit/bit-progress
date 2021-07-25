package com.wpx.exception;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/26 20:08
 * @Description 请求异常信息枚举
 */
public enum ExceptionMessage implements IExceptionMessage {

    /**
     * 请求参数错误或者参数为空
     */
    REQUEST_ARGUMENT(1001, ErrorCodes.AUTH_ACCOUNT_PASSWORD_WRONG, MessageCodes.AUTH_ACCOUNT_PASSWORD_WRONG),
    /**
     * 参数类型没有指定
     */
    TYPE_NOT_APPOINT(1002, ErrorCodes.TYPE_NOT_APPOINT, MessageCodes.TYPE_NOT_APPOINT),

    /**
     * 未经授权
     */
    AUTH_TOKEN_WRONG(2001, ErrorCodes.AUTH_TOKEN_WRONG, MessageCodes.AUTH_TOKEN_WRONG),
    /**
     * token为空
     */
    AUTH_TOKEN_EMPTY(2002, ErrorCodes.AUTH_TOKEN_EMPTY, MessageCodes.AUTH_TOKEN_EMPTY),
    /**
     * 账号密码出错
     */
    AUTH_ACCOUNT_PASSWORD_WRONG(2003, ErrorCodes.AUTH_ACCOUNT_PASSWORD_WRONG, MessageCodes.AUTH_ACCOUNT_PASSWORD_WRONG),
    /**
     * 验证码错误
     */
    AUTH_PICCAPTCHA_WRONG(2004, ErrorCodes.AUTH_PICCAPTCHA_WRONG, MessageCodes.AUTH_PICCAPTCHA_WRONG),
    /**
     * 验证码已失效
     */
    AUTH_PICCAPTCHA_LOST(2005, ErrorCodes.AUTH_PICCAPTCHA_LOST, MessageCodes.AUTH_PICCAPTCHA_LOST),
    /**
     * 解密失败
     */
    RSAUtil_DECRYPT_ERROR(2006, ErrorCodes.RSAUTIL_DECRYPT_ERROR, MessageCodes.RSAUTIL_DECRYPT_ERROR),

    /**
     * 账号已被禁用
     */
    ACCOUNT_HAS_DISABLED(2004, ErrorCodes.ACCOUNT_HAS_DISABLED, MessageCodes.ACCOUNT_HAS_DISABLED),

    /**
     * 账号已存在
     */
    ACCOUNT_ALREADY_EXIST(2005, ErrorCodes.ACCOUNT_ALREADY_EXIST, MessageCodes.ACCOUNT_ALREADY_EXIST),

    /* 用户相关错误信息 */

    /**
     * 非超管只能编辑自己的信息
     */
    NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO(2006, ErrorCodes.NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO, MessageCodes.NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO),
    /**
     * 非超管只能查看自己的信息
     */
    NOT_ROOT_ONLY_CAN_CHECK_SELF_INFO(2007, ErrorCodes.NOT_ROOT_ONLY_CAN_CHECK_SELF_INFO, MessageCodes.NOT_ROOT_ONLY_CAN_CHECK_SELF_INFO),
    /**
     * 不允许添加wpx之外的超管
     */
    NOT_ALLOW_ADD_WPX_EXCEPT_ROOT(2008, ErrorCodes.NOT_ALLOW_ADD_WPX_EXCEPT_ROOT, MessageCodes.NOT_ALLOW_ADD_WPX_EXCEPT_ROOT),
    /**
     * 超管不可删除
     */
    ROOT_CANNOT_DELETE(2009, ErrorCodes.ROOT_CANNOT_DELETE, MessageCodes.ROOT_CANNOT_DELETE),
    /**
     * 非超管不可删除账号
     */
    NOT_ROOT_CANNOT_DELETE(2010, ErrorCodes.NOT_ROOT_CANNOT_DELETE, MessageCodes.NOT_ROOT_CANNOT_DELETE),
    /**
     * 超管不可禁用
     */
    ROOT_CANNOT_DISABLED(2011, ErrorCodes.ROOT_CANNOT_DISABLED, MessageCodes.ROOT_CANNOT_DISABLED),
    /**
     * 非超管不可禁用账号
     */
    NOT_ROOT_CANNOT_DISABLED(2012, ErrorCodes.NOT_ROOT_CANNOT_DISABLED, MessageCodes.NOT_ROOT_CANNOT_DISABLED),
    /**
     * 不能给同级或上级设权限
     */
    NOT_ALLOW_SUPERIOR_ROLE(2013, ErrorCodes.NOT_ALLOW_SUPERIOR_ROLE, MessageCodes.NOT_ALLOW_SUPERIOR_ROLE),
    /**
     * 管理员记录不存在
     */
    MANAGER_NOT_EXIST(2014, ErrorCodes.MANAGER_NOT_EXIST, MessageCodes.MANAGER_NOT_EXIST),
    /**
     * 新增管理员失败
     */
    MANAGER_SAVE_ERROR(2015, ErrorCodes.MANAGER_SAVE_ERROR, MessageCodes.MANAGER_SAVE_ERROR),
    /**
     * 更新管理员信息失败
     */
    MANAGER_UPDATE_ERROR(2016, ErrorCodes.MANAGER_UPDATE_ERROR, MessageCodes.MANAGER_UPDATE_ERROR),
    /**
     * 管理员记录删除失败
     */
    MANAGER_DELETE_ERROR(2017, ErrorCodes.MANAGER_DELETE_ERROR, MessageCodes.MANAGER_DELETE_ERROR),

    /**
     * 应用信息不存在
     */
    APPLICATION_NOT_EXIST(3001, ErrorCodes.APPLICATION_NOT_EXIST, MessageCodes.APPLICATION_NOT_EXIST),
    /**
     * 应用信息保存失败
     */
    APPLICATION_SAVE_ERROR(3002, ErrorCodes.APPLICATION_SAVE_ERROR, MessageCodes.APPLICATION_SAVE_ERROR),
    /**
     * 应用信息更新失败
     */
    APPLICATION_UPDATE_ERROR(3003, ErrorCodes.APPLICATION_UPDATE_ERROR, MessageCodes.APPLICATION_UPDATE_ERROR),
    /**
     * 应用信息删除失败
     */
    APPLICATION_DELETE_ERROR(3004, ErrorCodes.APPLICATION_DELETE_ERROR, MessageCodes.APPLICATION_DELETE_ERROR),
    /**
     * 应用配置信息不存在
     */
    APPLICATIONINFO_NOT_EXIST(3005, ErrorCodes.APPLICATIONINFO_NOT_EXIST, MessageCodes.APPLICATIONINFO_NOT_EXIST),
    /**
     * 应用配置信息保存失败
     */
    APPLICATIONINFO_SAVE_ERROR(3006, ErrorCodes.APPLICATIONINFO_SAVE_ERROR, MessageCodes.APPLICATIONINFO_SAVE_ERROR),
    /**
     * 应用配置信息更新失败
     */
    APPLICATIONINFO_UPDATE_ERROR(3007, ErrorCodes.APPLICATIONINFO_UPDATE_ERROR, MessageCodes.APPLICATIONINFO_UPDATE_ERROR),
    /**
     * 应用配置信息删除失败
     */
    APPLICATIONINFO_DELETE_ERROR(3008, ErrorCodes.APPLICATIONINFO_DELETE_ERROR, MessageCodes.APPLICATIONINFO_DELETE_ERROR),
    /**
     * 应用主题不存在
     */
    APPLICATIONTOPIC_NOT_EXIST(3009, ErrorCodes.APPLICATIONTOPIC_NOT_EXIST, MessageCodes.APPLICATIONTOPIC_NOT_EXIST),
    /**
     * 应用主题保存失败
     */
    APPLICATIONTOPIC_SAVE_ERROR(3010, ErrorCodes.APPLICATIONTOPIC_SAVE_ERROR, MessageCodes.APPLICATIONTOPIC_SAVE_ERROR),
    /**
     * 应用主题更新失败
     */
    APPLICATIONTOPIC_UPDATE_ERROR(3011, ErrorCodes.APPLICATIONTOPIC_UPDATE_ERROR, MessageCodes.APPLICATIONTOPIC_UPDATE_ERROR),
    /**
     * 应用主题删除失败
     */
    APPLICATIONTOPIC_DELETE_ERROR(3012, ErrorCodes.APPLICATIONTOPIC_DELETE_ERROR, MessageCodes.APPLICATIONTOPIC_DELETE_ERROR),

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
        return null;
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

    public void setMessage(String message) {
        this.message = message;
    }

    ExceptionMessage(Integer code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

}
