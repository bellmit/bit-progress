package com.wpx.rest.feign;

import com.wpx.util.JsonUtils;
import com.wpx.model.ResultVO;
import com.wpx.model.login.TokenDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author 不会飞的小鹏
 */
@RestController
@RequestMapping("rest/gatewayCms/test")
public class TestFeignController {

    @GetMapping
    public ResultVO<String> test(@ModelAttribute TokenDTO tokenDTO) {
        String s = tokenDTO.toString();
        System.out.println(s);
        return ResultVO.successData(JsonUtils.serializeObject(tokenDTO));
    }

    @GetMapping("a")
    public ResultVO<String> testg(@ModelAttribute LoginDTO loginDTO) {
        String s = loginDTO.toString();
        System.out.println(s);
        return ResultVO.successData(JsonUtils.serializeObject(loginDTO));
    }

    @DeleteMapping("a")
    public ResultVO<String> testd(@ModelAttribute LoginDTO loginDTO) {
        String s = loginDTO.toString();
        System.out.println(s);
        return ResultVO.successData(JsonUtils.serializeObject(loginDTO));
    }

}
