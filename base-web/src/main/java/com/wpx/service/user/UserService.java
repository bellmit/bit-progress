package com.wpx.service.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.exception.BaseException;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.UserMapper;
import com.wpx.model.user.user.User;
import com.wpx.model.user.user.pojo.UserVO;
import com.wpx.util.Assert;
import com.wpx.util.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Author: 不会飞的小鹏
 * @Description: 用户信息服务类
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return: UserVO
     */
    public UserVO findUserById(Long userId) {
        User user = getUserById(userId);
        Assert.notNull(user, BaseException.error(BaseExceptionMessage.USER_NOT_EXIST_EXCEPTION));
        return toUserVO(user);
    }

    /**
     * 转换User
     *
     * @param user
     */
    private UserVO toUserVO(User user) {
        return BeanUtils.copyNonNullProperties(user, UserVO.class);
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId
     * @return User
     */
    public User getUserById(Long userId) {
        return getById(userId);
    }

}
