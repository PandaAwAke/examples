package org.example.rabbitmq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Slf4j
public class FirstConsumer {

    private final static String QUEUE_NAME = "hello";
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
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                log.info(" [x] Received '{}'", message);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
            log.info(" [*] Waiting for messages. To exit press CTRL+C");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}
