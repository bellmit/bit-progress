package com.wpx;

import com.wpx.model.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 不会飞的小鹏
 */
@FeignClient(name = "eureka-pro", path = "rest", fallbackFactory = FeignEurekaProServiceFallback.class)
public interface FeignEurekaProService {

    @GetMapping("test")
    ResultVO<Long> get(@RequestParam("id") Long id);

}
