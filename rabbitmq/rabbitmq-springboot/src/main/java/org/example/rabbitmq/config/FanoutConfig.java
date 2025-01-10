package org.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    private final static String QUEUE_NAME = "hello";
    private final static String EXCHANGE_NAME = "helloExchange";

    @Bean
    public Queue fanoutQueue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    public DirectExchange routingExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding bindQueue() {
        return BindingBuilder.bind(fanoutQueue()).to(routingExchange()).with("key1");
    }

}
