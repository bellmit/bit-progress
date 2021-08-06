package com.wpx.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.mapper.user.ManagerLogMapper;
import com.wpx.model.ResultVO;
import com.wpx.model.user.managerlog.ManagerLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private ManagerLogMapper managerLogMapper;

    @GetMapping
    @ApiOperation("测试")
    public ResultVO<List<ManagerLog>> getTest() {
        stringRedisTemplate1.opsForValue().set("aaa", "fahjkfsha");
        stringRedisTemplate.opsForValue().set("aaa", "12131231");
        ManagerLog managerLog = new ManagerLog();
        JSONObject object = new JSONObject();
        object.put("a", "a");
        managerLog.setManagerId(1L)
                .setArgs(object.toJSONString())
                .setMethod("ss")
                .setUri("aaa");
        managerLogMapper.insert(managerLog);
        managerLog.setManagerId(2L);
        managerLogMapper.updateById(managerLog);
        return ResultVO.successData(managerLogMapper.selectList(new QueryWrapper<>()));
    }

    @GetMapping("page")
    @ApiOperation("测试")
    public ResultVO<IPage<ManagerLog>> page(Page page) {
        return ResultVO.successData(managerLogMapper.selectPage(page, new QueryWrapper<>()));
    }

}
