package com.wpx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.gatewaycms.auth.AuthDubboService;
import com.wpx.mapper.user.ManagerLogMapper;
import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.user.managerlog.ManagerLog;
import com.wpx.service.RedisBaseService;
import com.wpx.util.RedisCacheUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 不会飞的小鹏
 *  测试接口
 */
@Api(tags = "测试")
@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("redis1StringRedisTemplate")
    private StringRedisTemplate redis1StringRedisTemplate;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    @Qualifier("redis1RedisCacheUtils")
    private RedisCacheUtils redis1RedisCacheUtils;

    @Autowired
    private RedisBaseService redisBaseService;

    @Autowired
    @Qualifier("redis1RedisBaseService")
    private RedisBaseService redis1RedisBaseService;

    @Autowired
    private ManagerLogMapper managerLogMapper;
    @Reference
    private AuthDubboService authDubboService;

    @GetMapping
    @ApiOperation("测试")
    public ResultVO<BooleanVO> getTest() {
        stringRedisTemplate.opsForValue().set("stringRedisTemplate", "stringRedisTemplate");
        redis1StringRedisTemplate.opsForValue().set("redis1Template", "redis1Template");
        redisCacheUtils.setForValue("redisCacheUtils", "redisCacheUtils");
        redis1RedisCacheUtils.setForValue("redisCacheUtils", "redisCacheUtils");
        redisBaseService.setForValue("redisBaseService", "redisBaseService");
        redis1RedisBaseService.setForValue("redis1BaseService", "redis1BaseService");
        return ResultVO.successData(new BooleanVO(true));
    }

    @GetMapping("page")
    @ApiOperation("测试")
    public ResultVO<IPage<ManagerLog>> page(Page page) {
        return ResultVO.successData(managerLogMapper.selectPage(page, new QueryWrapper<>()));
    }

    @GetMapping("user")
    @ApiOperation("获取用户信息")
    public ResultVO<LoginVO> getUser(@RequestParam("userId") Long userId) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserId(String.valueOf(userId));
        ResultVO<LoginVO> result = authDubboService.login(loginDTO);
        LoginVO loginVO = result.getData();
        return ResultVO.successData(loginVO);
    }

}
