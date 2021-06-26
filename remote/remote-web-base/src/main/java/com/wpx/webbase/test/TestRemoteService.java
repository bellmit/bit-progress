package com.wpx.webbase.test;

import com.wpx.model.user.manager.Manager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/23 0:50
 * @Description TestRemoteService is
 */
@FeignClient(name = "main-web-base")
public interface TestRemoteService {

    @RequestMapping(value = "rest/webBase/test", method = RequestMethod.GET)
    Manager test();

}
