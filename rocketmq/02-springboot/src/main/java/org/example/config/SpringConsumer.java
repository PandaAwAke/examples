package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "SpringBootConsumerGroup", topic = "TestTopic")
public class SpringConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("Received message: {}", message);
    }

}
