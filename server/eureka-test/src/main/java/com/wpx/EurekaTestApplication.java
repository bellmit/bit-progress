package com.wpx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 不会飞的小鹏
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaTestApplication.class, args);
    }

}
