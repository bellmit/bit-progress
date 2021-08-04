package com.wpx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: 测试接口
 */
@Api(tags = "测试")
@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("redis1")
    private StringRedisTemplate stringRedisTemplate1;

    @GetMapping
    @ApiOperation("测试")
    public String getTest() {
        stringRedisTemplate1.opsForValue().set("aaa", "fahjkfsha");
        stringRedisTemplate.opsForValue().set("aaa", "12131231");
        return "";
    }

}
