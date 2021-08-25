package com.wpx.rest.feign;

import com.wpx.model.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 不会飞的小鹏
 */
@RestController
@RequestMapping("rest")
public class TestController {

    @GetMapping("test")
    public ResultVO<Long> get(@RequestParam("id") Long id) {
        return ResultVO.successData(id);
    }

}
