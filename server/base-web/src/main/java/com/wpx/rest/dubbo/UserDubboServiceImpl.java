package com.wpx.rest.dubbo;

import com.wpx.baseweb.user.UserDubboService;
import com.wpx.model.ResultVO;
import com.wpx.model.user.user.User;
import com.wpx.service.user.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * @Description: dubbo获取用户信息实现类
 */
@Service
public class UserDubboServiceImpl implements UserDubboService {

    @Autowired
    private UserService userService;

    /**
     * 返回用户信息
     *
     * @param userId
     */
    @Override
    public ResultVO<User> getUser(Long userId) {
        return ResultVO.successData(userService.getUserById(userId));
    }

    /**
     * 获取用户信息
     *
     * @param userIds 用户ID集合
     * @return: ResultVO<List < User>>
     */
    @Override
    public ResultVO<List<User>> listUser(Set<Long> userIds) {
        return ResultVO.successData(userService.listUser(userIds));
    }

}
