package com.wpx;

import com.wpx.util.BeanManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class SchedulerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SchedulerApplication.class);
        BeanManager.setApplicationContext(context);
    }

}
