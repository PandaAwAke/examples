package org.example.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App03 {

    public static void main(String[] args) {
        var context = SpringApplication.run(App03.class, args);

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

    }

}
