package com.wpx;

import com.wpx.util.BeanManager;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/17 9:03
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement(proxyTargetClass = true)
@EnableDubbo
public class CmsBaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CmsBaseApplication.class, args);
        BeanManager.setApplicationContext(context);
    }

}
