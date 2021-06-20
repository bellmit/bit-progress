package com.wpx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 不会飞的小鹏
 * create on 2021/5/31 21:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class, args);
    }

}
