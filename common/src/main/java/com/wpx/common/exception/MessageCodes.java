package com.wpx.common.exception;

public class MessageCodes {

    public static final String REQUEST_ARGUMENT = "请求参数错误或者参数为空";
    public static final String AUTH_ACCOUNT_PASSWORD_WRONG = "账号或密码错误";
    public static final String INTERNAL_SERVER_ERROR = "server_internal";
    public static final String TYPE_NOT_APPOINT = "参数类型没有指定";
    public static final String AUTH_TOKEN_WRONG = "token错误或已过期,请重新登录";
    public static final String AUTH_TOKEN_EMPTY = "token为空";

    public static final String ACCOUNT_HAS_DISABLED = "账号已被禁用";
    public static final String ACCOUNT_ALREADY_EXIST = "账号已经存在";
    public static final String NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO = "非超管只能编辑自己的信息";
    public static final String NOT_ROOT_ONLY_CAN_CHECK_SELF_INFO = "非超管只能查看自己的信息";
    public static final String NOT_ALLOW_ADD_WPX_EXCEPT_ROOT = "不允许添加wpx之外的超管";
    public static final String MANAGER_NOT_EXIST = "管理员记录不存在";
    public static final String MANAGER_UPDATE_ERROR = "更新管理员信息失败";
    public static final String MANAGER_DELETE_ERROR = "管理员记录删除失败";
    public static final String MANAGER_SAVE_ERROR = "管理员记录保存失败";
    public static final String ROOT_CANNOT_DELETE = "超管不可删除";
    public static final String NOT_ROOT_CANNOT_DELETE = "非超管不可删除账号";
    public static final String ROOT_CANNOT_DISABLED = "超管不可禁用";
    public static final String NOT_ROOT_CANNOT_DISABLED = "非超管不可禁用账号";
    public static final String NOT_ALLOW_SUPERIOR_ROLE = "不能给同级或上级设权限";

    //验证码相关
    public static final String AUTH_PICCAPTCHA_WRONG = "验证码错误";
    public static final String AUTH_PICCAPTCHA_LOST = "验证码已失效";

    //加密解密相关
    public static final String RSAUtil_DECRYPT_ERROR = "解密失败";

    public static final String APPLICATION_NOT_EXIST = "应用信息不存在";
    public static final String APPLICATION_UPDATE_ERROR = "应用信息更新失败";
    public static final String APPLICATION_DELETE_ERROR = "应用信息删除失败";
    public static final String APPLICATION_SAVE_ERROR = "应用信息保存失败";

    public static final String APPLICATIONTOPIC_NOT_EXIST = "应用主题不存在";
    public static final String APPLICATIONTOPIC_UPDATE_ERROR = "应用主题更新失败";
    public static final String APPLICATIONTOPIC_DELETE_ERROR = "应用主题删除失败";
    public static final String APPLICATIONTOPIC_SAVE_ERROR = "应用主题保存失败";

    public static final String APPLET_NOT_EXIST = "小程序信息不存在";
    public static final String APPLET_SAVE_ERROR = "小程序信息保存失败";
    public static final String APPLET_UPDATE_ERROR = "小程序信息更新失败";
    public static final String APPLET_DELETE_ERROR = "小程序信息删除失败";

}
