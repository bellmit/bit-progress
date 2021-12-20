package com.wpx.controller;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 不会飞的小鹏
 */
@Api(tags = "测试")
@RestController
@RequestMapping("api/test")
public class TestController {

    @GetMapping
    @ApiOperation("测试")
    public ResultVO<BooleanVO> test() {
        System.out.println("aaa");
        return ResultVO.successData(BooleanVO.result(true));
    }

}
