package com.wpx.constant;

/**
 * @author 不会飞的小鹏
 * @description： 公众号模块接口url
 */
public class WechatOaUrl {

    /**
     * 添加客服url
     * POST
     */
    public static final String ADD_KF_URL = "https://api.weixin.qq.com/customservice/kfaccount/add";

    /**
     * 设置客服信息url
     * POST
     */
    public static final String UPDATE_KF_URL = "https://api.weixin.qq.com/customservice/kfaccount/update";

    /**
     * 删除客服信息url
     * GET
     */
    public static final String DELETE_KF_URL = "https://api.weixin.qq.com/customservice/kfaccount/del";

    /**
     * 上传客服头像url
     * POST
     */
    public static final String UPLOAD_HEAD_IMG_URL = "https://api.weixin.qq.com/customservice/kfaccount/uploadheadimg";

    /**
     * 获取客服列表url
     * GET
     */
    public static final String GET_KF_LIST_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist";

    /**
     * 邀请绑定客服账号url
     * POST
     */
    public static final String INVITE_WORKER_KF_URL = "https://api.weixin.qq.com/customservice/kfaccount/inviteworker";

    /**
     * 发送客服消息url
     * POST
     */
    public static final String SEND_KF_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

    /**
     * 创建客服会话url
     * POST
     */
    public static final String CREATE_KF_SESSION_URL = "https://api.weixin.qq.com/customservice/kfsession/create";

    /**
     * 关闭客服会话url
     * POST
     */
    public static final String CLOSE_KF_SESSION_URL = "https://api.weixin.qq.com/customservice/kfsession/close";

    /**
     * 获取客服会话url
     * GET
     */
    public static final String GET_KF_SESSION_URL = "https://api.weixin.qq.com/customservice/kfsession/getsession";

    /**
     * 获取客服会话列表url
     * GET
     */
    public static final String GET_KF_SESSION_LIST_URL = "https://api.weixin.qq.com/customservice/kfsession/getsessionlist";

    /**
     * 获取未接入会话列表url
     * GET
     */
    public static final String GET_WAIT_CASE_KF_SESSION_LIST_URL = "https://api.weixin.qq.com/customservice/kfsession/getwaitcase";

    /**
     * 获取聊天记录url
     * POST
     */
    public static final String GET_MSG_LIST_URL = "https://api.weixin.qq.com/customservice/msgrecord/getmsglist";

    /**
     * 客服输入状态url
     * POST
     */
    public static final String KF_SESSION_TYPING_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN";

}