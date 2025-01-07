package org.example.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;


@Slf4j
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.info("MyApplicationListener received event: {}", event);
    }

}
