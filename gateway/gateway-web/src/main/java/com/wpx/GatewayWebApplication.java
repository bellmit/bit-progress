package com.wpx;

import com.wpx.common.util.BeanManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 不会飞的小鹏
 * create on 2021/5/31 21:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GatewayWebApplication.class, args);
        BeanManager.setApplicationContext(context);
    }

}
