package com.wpx.baseweb.user;

import com.wpx.model.ResultVO;
import com.wpx.model.user.user.User;

/**
 * @Author: 不会飞的小鹏
 */
public interface UserDubboService {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return: ResultVO<User>
     */
    ResultVO<User> getUser(Long userId);

}
