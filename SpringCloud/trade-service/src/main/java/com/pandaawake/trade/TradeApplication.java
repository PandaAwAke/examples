package com.pandaawake.trade;


import com.pandaawake.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.pandaawake.trade.mapper")
@EnableFeignClients(basePackages = "com.pandaawake.api.client", defaultConfiguration = DefaultFeignConfig.class)
public class TradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }

}
