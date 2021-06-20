package com.wpx.common.exception;

public class MessageCodes {

    public static final String AUTH_ACCOUNT_PASSWORD_WRONG = "auth_account_password_wrong";//账号或密码错误
    public static final String REQUEST_ARGUMENT = "request_argument";//请求参数错误或者参数为空
    public static final String INTERNAL_SERVER_ERROR = "server_internal";//未知错误
    public static final String TYPE_NOT_APPOINT = "type_not_appoint"; // 类型没有指定
    public static final String AUTH_TOKEN = "auth_token_wrong";//token错误或已过期,请重新登录

    public static final String AUTH_TOKEN_EMPTY = "auth_token_empty";//token为空
    public static final String ACCOUNT_HAS_DISABLED = "account_has_disabled";//账号已被禁用
    public static final String ACCOUNT_ALREADY_EXIST = "account_already_exist";//账号已经存在
    public static final String NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO = "not_root_only_can_edit_self_info";//非超管只能编辑自己的信息
    public static final String NOT_ROOT_ONLY_CAN_CHECK_SELF_INFO = "not_root_only_can_edit_self_info";//非超管只能查看自己的信息
    public static final String NOT_ALLOW_ADD_LUWEI_EXCEPT_ROOT = "not_allow_add_luwei_except_root";//不允许添加luwei之外的超管
    public static final String MANAGER_NOT_EXIST = "manager_not_exist";   //管理员记录不存在
    public static final String MANAGER_UPDATE_ERROR = "manager_update_error"; //管理员记录更新失败
    public static final String MANAGER_DELETE_ERROR = "manager_delete_error"; //管理员记录删除失败
    public static final String MANAGER_SAVE_ERROR = "manager_save_error"; //管理员记录保存失败
    public static final String ROOT_CANNOT_DELETE = "root_cannot_delete";//超管不可删除
    public static final String NOT_ROOT_CANNOT_DELETE = "not_root_cannot_delete";//非超管不可删除账号
    public static final String ROOT_CANNOT_DISABLED = "root_cannot_disabled";//超管不可禁用
    public static final String NOT_ROOT_CANNOT_DISABLED = "not_root_cannot_disabled";//非超管不可禁用账号
    public static final String NOT_ALLOW_SUPERIOR_ROLE = "not_allow_superior_role";//不能给同级或上级设权限

    //验证码相关
    public static final String AUTH_PICCAPTCHA_WRONG = "auth_captcha_wrong";//验证码错误
    public static final String AUTH_PICCAPTCHA_LOST = "auth_captcha_lost";//验证码已失效

    //加密解密相关
    public static final String RSAUtil_DECRYPT_ERROR = "rsautil_decrypt_error";//解密失败

    //用户相关
    public static final String USER_NOT_EXIST = "user_not_exist";//用户不存在
    public static final String USER_UPDATE_ERROR = "user_update_error"; //用户记录记录更新失败
    public static final String USER_DELETE_ERROR = "user_delete_error"; //用户记录记录删除失败
    public static final String USER_SAVE_ERROR = "user_save_error"; //用户记录记录保存失败

    public static final String ADPLATFORM_NOT_EXIST = "adplatform_not_exist";   //记录不存在
    public static final String ADPLATFORM_UPDATE_ERROR = "adplatform_update_error"; //记录更新失败
    public static final String ADPLATFORM_DELETE_ERROR = "adplatform_delete_error"; //记录删除失败
    public static final String ADPLATFORM_SAVE_ERROR = "adplatform_save_error"; //记录保存失败

    public static final String ADPLATFORMSHOWCONFIG_NOT_EXIST = "adplatformshowconfig_not_exist";   //记录不存在
    public static final String ADPLATFORMSHOWCONFIG_UPDATE_ERROR = "adplatformshowconfig_update_error"; //记录更新失败
    public static final String ADPLATFORMSHOWCONFIG_DELETE_ERROR = "adplatformshowconfig_delete_error"; //记录删除失败
    public static final String ADPLATFORMSHOWCONFIG_SAVE_ERROR = "adplatformshowconfig_save_error"; //记录保存失败

    public static final String CLEANERPATH_OPERATION_TO_OFEN = "cleanerpath_operation_to_ofen"; // 缓存清理路径操作太频繁

    public static final String APPLICATION_NOT_EXIST = "application_not_exist";   //应用信息记录不存在
    public static final String APPLICATION_UPDATE_ERROR = "application_update_error"; //应用信息记录更新失败
    public static final String APPLICATION_DELETE_ERROR = "application_delete_error"; //应用信息记录删除失败
    public static final String APPLICATION_SAVE_ERROR = "application_save_error"; //应用信息记录保存失败

    public static final String APPLICATIONTOPIC_NOT_EXIST = "applicationtopic_not_exist";   //应用主题记录不存在
    public static final String APPLICATIONTOPIC_UPDATE_ERROR = "applicationtopic_update_error"; //应用主题记录更新失败
    public static final String APPLICATIONTOPIC_DELETE_ERROR = "applicationtopic_delete_error"; //应用主题记录删除失败
    public static final String APPLICATIONTOPIC_SAVE_ERROR = "applicationtopic_save_error"; //应用主题记录保存失败

}
