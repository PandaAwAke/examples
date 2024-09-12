package org.example.boot;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "org.example.boot.mapper")
@SpringBootApplication
public class App05 {

    public static void main(String[] args) {
        SpringApplication.run(App05.class, args);
    }

}
