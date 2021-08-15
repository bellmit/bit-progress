package com.wpx.rest.feign;

import com.wpx.model.ResultVO;
import com.wpx.model.user.manager.Manager;
import com.wpx.model.user.user.User;
import com.wpx.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/23 0:45
 * @Description TestRemoteController is
 */
@RestController
@RequestMapping("rest/baseWeb/user")
public class UserFeignController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param userId  用户ID
     * @return: ResultVO<User>
     */
    @GetMapping
    public ResultVO<User> getUser(@RequestParam Long userId) {
        return ResultVO.successData(userService.getUserById(userId));
    }

    /**
     * 获取用户信息
     *
     * @param userIds  用户ID集合
     * @return: ResultVO<List<User>>
     */
    @GetMapping("list")
    public ResultVO<List<User>> listUser(@RequestParam Set<Long> userIds) {
        return ResultVO.successData(userService.listUser(userIds));
    }

}
