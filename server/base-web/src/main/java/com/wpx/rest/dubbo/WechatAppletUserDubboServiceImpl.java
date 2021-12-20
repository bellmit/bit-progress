package com.wpx.rest.dubbo;

import com.wpx.baseweb.user.WechatAppletUserDubboService;
import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatappletuser.WechatAppletUser;
import com.wpx.service.user.WechatAppletUserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 *  wechatAppleUser dubbo remote
 */
@Service(version = "1.0.0")
public class WechatAppletUserDubboServiceImpl implements WechatAppletUserDubboService {

    @Autowired
    private WechatAppletUserService wechatAppletUserService;

    /**
     * 获取微信用户信息
     *
     * @param wechatAppletUserId 微信用户ID
     * @return ResultVO<WechatAppletUser>
     */
    @Override
    public ResultVO<WechatAppletUser> getWechatAppletUser(Long wechatAppletUserId) {
        return ResultVO.successData(wechatAppletUserService.getWechatAppletUserById(wechatAppletUserId));
    }

    /**
     * 获取微信用户信息
     *
     * @param wechatAppletUserIds 微信用户ID集合
     * @return ResultVO<List < WechatAppletUser>>
     */
    @Override
    public ResultVO<List<WechatAppletUser>> listWechatAppletUser(Set<Long> wechatAppletUserIds) {
        return ResultVO.successData(wechatAppletUserService.listWechatAppletUserByIds(wechatAppletUserIds));
    }

    /**
     * 获取微信用户信息
     *
     * @param userIds 用户ID集合
     * @return ResultVO<List < WechatAppletUser>>
     */
    @Override
    public ResultVO<List<WechatAppletUser>> listWechatAppletUserByUserIds(Set<Long> userIds) {
        return ResultVO.successData(wechatAppletUserService.listWechatAppletUserByUserIds(userIds));
    }

}
