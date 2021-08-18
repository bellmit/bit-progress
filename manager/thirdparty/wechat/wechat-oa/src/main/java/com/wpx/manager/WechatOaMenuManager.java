package com.wpx.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wpx.constant.WechatOaUrl;
import com.wpx.model.WechatResult;
import com.wpx.model.menu.dto.MenuDTO;
import com.wpx.model.menu.vo.MenuVO;
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
     * @param menuDTO
     * @return WechatResult
     */
    public WechatResult createMenu(String accessToken, MenuDTO menuDTO) {
        String body = JSON.toJSONString(menuDTO);
        String result = WechatRequestUtils.doPostWithAccessToken(WechatOaUrl.CREATE_MENU_URL, accessToken, body);
        return WechatResultUtils.wechatResultCheck(result);
    }

    /**
     * 获取菜单
     *
     * @return MenuVO
     */
    public MenuVO getMenu(String accessToken) {
        String result = WechatRequestUtils.doGetWithAccessToken(WechatOaUrl.GET_MENU_URL, accessToken);
        return WechatResultUtils.wechatResultCheck(result, MenuVO.class);
    }

}
