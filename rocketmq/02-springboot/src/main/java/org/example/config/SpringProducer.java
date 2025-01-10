package org.example.config;

import jakarta.annotation.Resource;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SpringProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String msg) {
//        rocketMQTemplate.send(topic, MessageBuilder.withPayload(msg.getBytes(StandardCharsets.UTF_8)).build());
//        rocketMQTemplate.syncSend(topic, msg);
        rocketMQTemplate.convertAndSend(topic, msg);    // 直接发字符串（转换成 Message）
    }

    public void sendMessageInTransaction(String topic, String msg) throws InterruptedException {
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            Message<String> message = MessageBuilder.withPayload(msg)
                    .setHeader(RocketMQHeaders.TRANSACTION_ID, "TransID_" + i)
                    .setHeader(RocketMQHeaders.TAGS, tags[i % tags.length])
                    .setHeader("MyProp", "MyProp_i")
                    .build();

            String destination = topic  + ":" + tags[i % tags.length];

            SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, message, null);
            System.out.printf("%s%n", sendResult);

            Thread.sleep(10);
        }
    }

}
