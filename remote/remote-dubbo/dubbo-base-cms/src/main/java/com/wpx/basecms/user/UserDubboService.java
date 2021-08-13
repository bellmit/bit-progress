package com.wpx.basecms.user;

import com.wpx.model.ResultVO;
import com.wpx.model.user.user.User;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 */
public interface UserDubboService {

    /**
     * 获取用户信息
     *
     * @param userId  用户ID
     * @return: ResultVO<User>
     */
    ResultVO<User> getUser(Long userId);

    /**
     * 获取用户信息
     *
     * @param userIds  用户ID集合
     * @return: ResultVO<List<User>>
     */
    ResultVO<List<User>> listUser(Set<Long> userIds);

}
