package com.wpx.gatewaycms.route;

import com.wpx.model.ResultVO;
import com.wpx.model.login.TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 不会飞的小鹏
 */
@FeignClient(name = "gateway-cms", path = "rest/gatewayCms/test", fallbackFactory = TestFeignServiceFallback.class)
public interface TestFeignService {

    @GetMapping
    ResultVO<String> getToken(@SpringQueryMap TokenDTO o);

}
