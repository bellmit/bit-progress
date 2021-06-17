package com.wpx;

import com.wpx.common.util.BeanManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/14 19:34
 * @Description Cms端网关
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayCmsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GatewayCmsApplication.class, args);
        BeanManager.setApplicationContext(context);
    }

}
