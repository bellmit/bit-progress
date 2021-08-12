package com.wpx;

import com.wpx.util.BeanManager;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/22 23:44
 * @Description WebBaseApplication is
 */
@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement(proxyTargetClass = true)
@EnableDiscoveryClient
@EnableFeignClients
public class WebBaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebBaseApplication.class, args);
        BeanManager.setApplicationContext(context);
    }

}
