package com.pandaawake.user;


import com.pandaawake.api.config.DefaultFeignConfig;
import com.pandaawake.common.config.SecurityConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("com.pandaawake.user.mapper")
@EnableFeignClients(basePackages = "com.pandaawake.api.client", defaultConfiguration = DefaultFeignConfig.class)
@Import({SecurityConfig.class})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
