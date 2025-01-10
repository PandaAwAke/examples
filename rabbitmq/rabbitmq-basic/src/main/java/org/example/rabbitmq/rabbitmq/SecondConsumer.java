package org.example.rabbitmq.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Slf4j
public class SecondConsumer {

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

            channel.basicQos(1);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    log.info("consumerTag: {}", consumerTag);
                    log.info("routingKey: {}", envelope.getRoutingKey());
                    log.info("contentType: {}", properties.getContentType());
                    log.info("deliveryTag: {}", envelope.getDeliveryTag());
                    log.info("correlationId: {}", properties.getCorrelationId());

                    log.info("content: {}", new String(body, StandardCharsets.UTF_8));
                    super.handleDelivery(consumerTag, envelope, properties, body);
                }
            };

            channel.basicConsume(QUEUE_NAME, true, consumer);
            log.info(" [*] Waiting for messages. To exit press CTRL+C");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}
