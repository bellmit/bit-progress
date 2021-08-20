package com.wpx.rest.dubbo;

import com.wpx.baseweb.user.WechatUserDubboService;
import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatuser.WechatUser;
import com.wpx.service.user.WechatUserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * @description： wechatUser dubbo remote
 */
@Service
public class WechatUserDubboServiceImpl implements WechatUserDubboService {

    @Autowired
    private WechatUserService wechatUserService;

    /**
     * 获取微信用户信息
     *
     * @param wechatUserId 微信用户ID
     * @return: ResultVO<WechatUser>
     */
    @Override
    public ResultVO<WechatUser> getWechatUser(Long wechatUserId) {
        return ResultVO.successData(wechatUserService.getWechatUserById(wechatUserId));
    }

    /**
     * 获取微信用户信息
     *
     * @param wechatUserIds 微信用户ID集合
     * @return: ResultVO<List < WechatUser>>
     */
    @Override
    public ResultVO<List<WechatUser>> listWechatUser(Set<Long> wechatUserIds) {
        return ResultVO.successData(wechatUserService.listWechatUser(wechatUserIds));
    }

}
