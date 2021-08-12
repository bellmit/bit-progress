package com.wpx.exception;

/**
 * @Author: 不会飞的小鹏
 * @Description: 基础模块错误信息
 */
public class BaseMessageCodes {

    public static final String NOT_ROOT_EDIT_OTHER_INFO_MESSAGE = "非超管只能编辑自己的信息";
    public static final String NOT_ROOT_CHECK_OTHER_INFO_MESSAGE = "非超管只能查看自己的信息";
    public static final String ALLOW_ADD_WPX_EXCEPT_ROOT_MESSAGE = "不允许添加wpx之外的超管";
    public static final String MANAGER_NOT_EXIST_EXCEPTION_MESSAGE = "管理员记录不存在";
    public static final String MANAGER_UPDATE_EXCEPTION_MESSAGE = "更新管理员信息失败";
    public static final String MANAGER_DELETE_EXCEPTION_MESSAGE = "管理员记录删除失败";
    public static final String MANAGER_SAVE_EXCEPTION_MESSAGE = "管理员记录保存失败";
    public static final String ROOT_DELETE_EXCEPTION_MESSAGE_MESSAGE = "超管不可删除";
    public static final String NOT_ROOT_CANNOT_DELETE_MESSAGE = "非超管不可删除账号";
    public static final String ROOT_CANNOT_DISABLED_MESSAGE = "超管不可禁用";
    public static final String NOT_ROOT_CANNOT_DISABLED_MESSAGE = "非超管不可禁用账号";
    public static final String NOT_ALLOW_SUPERIOR_ROLE_MESSAGE = "不能给同级或上级设权限";

    public static final String APPLICATION_NOT_EXIST = "应用信息不存在";
    public static final String APPLICATION_UPDATE_EXCEPTION_MESSAGE = "应用信息更新失败";
    public static final String APPLICATION_DELETE_EXCEPTION_MESSAGE = "应用信息删除失败";
    public static final String APPLICATION_SAVE_EXCEPTION_MESSAGE = "应用信息保存失败";
    public static final String APPLICATIONINFO_NOT_EXIST = "应用配置信息不存在";
    public static final String APPLICATIONINFO_SAVE_EXCEPTION_MESSAGE = "应用配置信息保存失败";
    public static final String APPLICATIONINFO_UPDATE_EXCEPTION_MESSAGE = "应用配置信息更新失败";
    public static final String APPLICATIONINFO_DELETE_EXCEPTION_MESSAGE = "应用配置信息删除失败";
    public static final String APPLICATIONTOPIC_NOT_EXIST = "应用主题不存在";
    public static final String APPLICATIONTOPIC_SAVE_EXCEPTION_MESSAGE = "应用主题保存失败";
    public static final String APPLICATIONTOPIC_UPDATE_EXCEPTION_MESSAGE = "应用主题更新失败";
    public static final String APPLICATIONTOPIC_DELETE_EXCEPTION_MESSAGE = "应用主题删除失败";
    public static final String USER_NOT_EXIST = "用户不存在";
    public static final String USER_SAVE_EXCEPTION_MESSAGE = "用户保存失败";
    public static final String USER_UPDATE_EXCEPTION_MESSAGE = "用户更新失败";
    public static final String USER_DELETE_EXCEPTION_MESSAGE = "用户删除失败";

}
