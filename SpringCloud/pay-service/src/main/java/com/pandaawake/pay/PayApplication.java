package com.pandaawake.pay;


import com.pandaawake.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.pandaawake.pay.mapper")
@EnableFeignClients(basePackages = "com.pandaawake.api.client", defaultConfiguration = DefaultFeignConfig.class)
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

}
