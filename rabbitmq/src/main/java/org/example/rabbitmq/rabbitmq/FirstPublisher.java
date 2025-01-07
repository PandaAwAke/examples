package org.example.rabbitmq.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Slf4j
public class FirstPublisher {

    private final static String QUEUE_NAME = "hello";
    private final static String EXCHANGE_NAME = "helloExchange";
    private final static String VIRTUAL_HOST_NAME = "/myvhost";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost(VIRTUAL_HOST_NAME);

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            Map<String, Object> arguments = new HashMap<>();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, arguments);
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "key1");

            String message1 = "test1";
            channel.basicPublish(EXCHANGE_NAME, "key1", MessageProperties.PERSISTENT_TEXT_PLAIN, message1.getBytes());

            String message2 = "test2";
            channel.basicPublish(EXCHANGE_NAME, "key1", MessageProperties.PERSISTENT_TEXT_PLAIN, message2.getBytes());
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}
