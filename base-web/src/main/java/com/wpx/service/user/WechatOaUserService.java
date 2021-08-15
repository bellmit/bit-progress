package com.wpx.service.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.mapper.user.WechatOaUserMapper;
import com.wpx.model.user.wechatoauser.WechatOaUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* <p>
    * 微信公众号用户信息 服务类
    * </p>
*
* @author 不会飞的小鹏
* @since 2021-08-14
*/
@Service
@Slf4j
public class WechatOaUserService extends ServiceImpl<WechatOaUserMapper, WechatOaUser> {

}
