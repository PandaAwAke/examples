package com.pandaawake.user;


import com.pandaawake.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.pandaawake.user.mapper")
@EnableFeignClients(basePackages = "com.pandaawake.api.client", defaultConfiguration = DefaultFeignConfig.class)
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
