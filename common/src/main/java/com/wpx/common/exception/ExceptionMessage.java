package com.wpx.common.exception;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/26 20:08
 * @Description 请求异常信息枚举
 */
public enum ExceptionMessage implements IExceptionMessage {

    /**
     * 请求参数错误或者参数为空
     */
    REQUEST_ARGUMENT(1001, MessageCodes.AUTH_ACCOUNT_PASSWORD_WRONG),
    /**
     * 参数类型没有指定
     */
    TYPE_NOT_APPOINT(1002, MessageCodes.TYPE_NOT_APPOINT),

    /**
     * 未经授权
     */
    AUTH_TOKEN_WRONG(2001, MessageCodes.AUTH_TOKEN_WRONG),
    /**
     * token为空
     */
    AUTH_TOKEN_EMPTY(2002, MessageCodes.AUTH_TOKEN_EMPTY),
    /**
     * 账号密码出错
     */
    AUTH_ACCOUNT_PASSWORD_WRONG(2003, MessageCodes.AUTH_ACCOUNT_PASSWORD_WRONG),
    /**
     * 验证码错误
     */
    AUTH_PICCAPTCHA_WRONG(2004, MessageCodes.AUTH_PICCAPTCHA_WRONG),
    /**
     * 验证码已失效
     */
    AUTH_PICCAPTCHA_LOST(2005, MessageCodes.AUTH_PICCAPTCHA_LOST),
    /**
     * 解密失败
     */
    RSAUtil_DECRYPT_ERROR(2006, MessageCodes.RSAUtil_DECRYPT_ERROR),

    /**
     * 账号已被禁用
     */
    ACCOUNT_HAS_DISABLED(2004, MessageCodes.ACCOUNT_HAS_DISABLED),

    /**
     * 账号已存在
     */
    ACCOUNT_ALREADY_EXIST(2005, MessageCodes.ACCOUNT_ALREADY_EXIST),

    /* 用户相关错误信息 */

    /**
     * 非超管只能编辑自己的信息
     */
    NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO(2006, MessageCodes.NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO),
    /**
     * 非超管只能查看自己的信息
     */
    NOT_ROOT_ONLY_CAN_CHECK_SELF_INFO(2007, MessageCodes.NOT_ROOT_ONLY_CAN_CHECK_SELF_INFO),
    /**
     * 不允许添加wpx之外的超管
     */
    NOT_ALLOW_ADD_WPX_EXCEPT_ROOT(2008, MessageCodes.NOT_ALLOW_ADD_WPX_EXCEPT_ROOT),
    /**
     * 超管不可删除
     */
    ROOT_CANNOT_DELETE(2009, MessageCodes.ROOT_CANNOT_DELETE),
    /**
     * 非超管不可删除账号
     */
    NOT_ROOT_CANNOT_DELETE(2010, MessageCodes.NOT_ROOT_CANNOT_DELETE),
    /**
     * 超管不可禁用
     */
    ROOT_CANNOT_DISABLED(2011, MessageCodes.ROOT_CANNOT_DISABLED),
    /**
     * 非超管不可禁用账号
     */
    NOT_ROOT_CANNOT_DISABLED(2012, MessageCodes.NOT_ROOT_CANNOT_DISABLED),
    /**
     * 不能给同级或上级设权限
     */
    NOT_ALLOW_SUPERIOR_ROLE(2013, MessageCodes.NOT_ALLOW_SUPERIOR_ROLE),
    /**
     * 管理员记录不存在
     */
    MANAGER_NOT_EXIST(2014, MessageCodes.MANAGER_NOT_EXIST),
    /**
     * 新增管理员失败
     */
    MANAGER_SAVE_ERROR(2015, MessageCodes.MANAGER_SAVE_ERROR),
    /**
     * 更新管理员信息失败
     */
    MANAGER_UPDATE_ERROR(2016, MessageCodes.MANAGER_UPDATE_ERROR),
    /**
     * 管理员记录删除失败
     */
    MANAGER_DELETE_ERROR(2017, MessageCodes.MANAGER_DELETE_ERROR),

    /**
     * 应用信息不存在
     */
    APPLICATION_NOT_EXIST(3001, MessageCodes.APPLICATION_NOT_EXIST),
    /**
     * 应用信息保存失败
     */
    APPLICATION_SAVE_ERROR(3002, MessageCodes.APPLICATION_SAVE_ERROR),
    /**
     * 应用信息更新失败
     */
    APPLICATION_UPDATE_ERROR(3003, MessageCodes.APPLICATION_UPDATE_ERROR),
    /**
     * 应用信息删除失败
     */
    APPLICATION_DELETE_ERROR(3004, MessageCodes.APPLICATION_DELETE_ERROR),
    /**
     * 应用配置信息不存在
     */
    APPLICATIONINFO_NOT_EXIST(3005, MessageCodes.APPLICATIONINFO_NOT_EXIST),
    /**
     * 应用配置信息保存失败
     */
    APPLICATIONINFO_SAVE_ERROR(3006, MessageCodes.APPLICATIONINFO_SAVE_ERROR),
    /**
     * 应用配置信息更新失败
     */
    APPLICATIONINFO_UPDATE_ERROR(3007, MessageCodes.APPLICATIONINFO_UPDATE_ERROR),
    /**
     * 应用配置信息删除失败
     */
    APPLICATIONINFO_DELETE_ERROR(3008, MessageCodes.APPLICATIONINFO_DELETE_ERROR),
    /**
     * 应用主题不存在
     */
    APPLICATIONTOPIC_NOT_EXIST(3009, MessageCodes.APPLICATIONTOPIC_NOT_EXIST),
    /**
     * 应用主题保存失败
     */
    APPLICATIONTOPIC_SAVE_ERROR(3010, MessageCodes.APPLICATIONTOPIC_SAVE_ERROR),
    /**
     * 应用主题更新失败
     */
    APPLICATIONTOPIC_UPDATE_ERROR(3011, MessageCodes.APPLICATIONTOPIC_UPDATE_ERROR),
    /**
     * 应用主题删除失败
     */
    APPLICATIONTOPIC_DELETE_ERROR(3012, MessageCodes.APPLICATIONTOPIC_DELETE_ERROR),

    /**
     * JSAPI支付统一下单失败
     */
    JSAPI_UNIFIED_ORDER_ERROR(4001, MessageCodes.JSAPI_UNIFIED_ORDER_ERROR),
    /**
     * JSAPI支付信息签名失败
     */
    JSAPI_PAY_SIGN_ERROR(4001, MessageCodes.JSAPI_PAY_SIGN_ERROR),

    ;

    /**
     * 状态码
     */
    private Integer code;

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

    ExceptionMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
