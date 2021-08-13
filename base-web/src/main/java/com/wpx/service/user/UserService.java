package com.wpx.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.UserMapper;
import com.wpx.model.user.user.User;
import com.wpx.model.user.user.pojo.web.UserWebVO;
import com.wpx.util.BeanUtils;
import com.wpx.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import com.wpx.util.Assert;

/**
* <p>
    * 用户信息 服务类
    * </p>
*
* @author 不会飞的小鹏
* created on 2021-08-13
*/
@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper, User> {

    public UserWebVO findById(Long userId) {
        User user = getUserById(userId);
        Assert.notNull(user, BaseExceptionMessage.USER_NOT_EXIST_EXCEPTION);
        return toUserWebVO(user);
    }

    public User getUserById(Long userId) {
        return getById(userId);
    }

    private UserWebVO toUserWebVO(User user) {
        return BeanUtils.copyNonNullProperties(user, UserWebVO.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public UserWebVO saveUser(User user) {
        LocalDateTime time=LocalDateTime.now();
        user.setUpdateTime(time).setCreateTime(time).setDeleted(false);
        Assert.isTrue(save(user), BaseExceptionMessage.USER_SAVE_EXCEPTION);
        return toUserWebVO(user);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteUsers(Set<Long> userIds) {
        int count = baseMapper.deleteBatchIds(userIds);
        Assert.isTrue(count == userIds.size(), BaseExceptionMessage.USER_DELETE_EXCEPTION);
    }

    /**
     * 根据ID集合查询用户信息
     *
     * @param userIds  用户ID集合
     */
    public List<User> listUser(Set<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        lambda.in(User::getUserId, userIds);
        return list(lambda);
    }

}
