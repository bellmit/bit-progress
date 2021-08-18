package com.wpx.manager;

import com.alibaba.fastjson.JSON;
import com.wpx.constant.WechatOaUrl;
import com.wpx.model.WechatResult;
import com.wpx.model.menu.Menu;
import com.wpx.util.WechatRequestUtils;
import com.wpx.util.WechatResultUtils;

/**
 * @author 不会飞的小鹏
 * @description： 微信公众号菜单管理
 */
public class WechatOaMenuManager {

    /**
     * 创建菜单
     *
     * @param menu
     * @return WechatResult
     */
    public WechatResult createMenu(String accessToken, Menu menu) {
        String body = JSON.toJSONString(menu);
        String result = WechatRequestUtils.doPostWithAccessToken(WechatOaUrl.CREATE_MENU_URL, accessToken, body);
        return WechatResultUtils.wechatResultCheck(result);
    }

}
