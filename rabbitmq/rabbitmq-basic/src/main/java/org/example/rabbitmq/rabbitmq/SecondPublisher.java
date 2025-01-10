package org.example.rabbitmq.rabbitmq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Slf4j
public class SecondPublisher {

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

            channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    log.info("ack: {}", deliveryTag);
                }

                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    log.info("nack: {}", deliveryTag);
                }
            });

            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    log.info("return: {}, {}", replyCode, replyText);
                }
            });

            Map<String, Object> arguments = new HashMap<>();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, arguments);
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "key1");

            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            builder.deliveryMode(MessageProperties.PERSISTENT_TEXT_PLAIN.getDeliveryMode());
            builder.priority(MessageProperties.PERSISTENT_TEXT_PLAIN.getPriority());
            builder.correlationId("111");

            String message1 = "test1";
            channel.basicPublish(EXCHANGE_NAME, "key1", true, builder.build(), message1.getBytes());
            channel.basicPublish(EXCHANGE_NAME, "non-exist-key", true, builder.build(), message1.getBytes());


        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}
