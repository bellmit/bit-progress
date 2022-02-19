package com.bitprogress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 不会飞的小鹏
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EurekaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaTestApplication.class, args);
    }

}
