package com.wpx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/14 19:34
 * Cms端网关
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayCmsApplication.class, args);
    }

}
