package org.example.rabbitmq;

import jakarta.annotation.Resource;
import org.example.rabbitmq.config.MyApplicationListener;
import org.example.rabbitmq.event.MyEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MainApplication.class);
        app.addListeners(new MyApplicationListener());
        app.run(args);
    }

    @Resource
    private ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        context.publishEvent(new MyEvent("myEvent"));
    }

}
