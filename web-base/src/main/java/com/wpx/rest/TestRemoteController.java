package com.wpx.rest;

import com.wpx.model.system.manager.Manager;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/23 0:45
 * @Description TestRemoteController is
 */
@RestController
@RequestMapping("rest/webBase/test")
public class TestRemoteController {

    @GetMapping
    public Manager test() {
        System.out.println();
        return new Manager().setUpdateTime(LocalDateTime.now());
    }

}
