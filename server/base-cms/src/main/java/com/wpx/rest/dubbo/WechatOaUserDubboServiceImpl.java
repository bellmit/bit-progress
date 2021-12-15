package com.wpx.rest.dubbo;

import com.wpx.basecms.user.WechatOaUserDubboService;
import com.wpx.model.ResultVO;
import com.wpx.model.user.wechatoauser.WechatOaUser;
import com.wpx.service.user.WechatOaUserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * wechatOaUser dubbo remote impl
 */
@Service(version = "1.0.0")
public class WechatOaUserDubboServiceImpl implements WechatOaUserDubboService {

    @Autowired
    private WechatOaUserService wechatOaUserService;

    /**
     * 获取微信公众号用户信息
     *
     * @param wechatOaUserId  微信公众号用户ID
     * @return ResultVO<WechatOaUser>
     */
    @Override
    public ResultVO<WechatOaUser> getWechatOaUser(Long wechatOaUserId) {
        return ResultVO.successData(wechatOaUserService.getWechatOaUserById(wechatOaUserId));
    }

    /**
     * 获取微信公众号用户信息
     *
     * @param wechatOaUserIds  微信公众号用户ID集合
     * @return ResultVO<List<WechatOaUser>>
     */
    @Override
    public ResultVO<List<WechatOaUser>> listWechatOaUser(Set<Long> wechatOaUserIds) {
        return ResultVO.successData(wechatOaUserService.listWechatOaUserByIds(wechatOaUserIds));
    }

    /**
     * 获取公众号微信用户信息
     *
     * @param userIds  用户ID集合
     * @return ResultVO<List<WechatOaUser>>
     */
    @Override
    public ResultVO<List<WechatOaUser>> listWechatOaUserByUserIds(Set<Long> userIds) {
        return ResultVO.successData(wechatOaUserService.listWechatOaUserByUserIds(userIds));
    }

}
