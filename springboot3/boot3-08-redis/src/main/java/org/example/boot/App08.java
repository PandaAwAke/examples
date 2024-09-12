package org.example.boot;


import org.example.boot.service.RedisService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App08 {

    public static void main(String[] args) {
        var context = SpringApplication.run(App08.class, args);

        context.getBean(RedisService.class).testRedis();
    }

}
