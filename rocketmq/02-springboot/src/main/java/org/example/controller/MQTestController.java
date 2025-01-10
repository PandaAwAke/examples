package org.example.controller;

import jakarta.annotation.Resource;
import org.example.config.SpringProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MQTest")
public class MQTestController {

    private static final String topic = "TestTopic";

    @Resource
    private SpringProducer producer;

    @RequestMapping("/sendMessage")
    public String sendMessage(String message) {
        producer.sendMessage(topic, message);
        return "Success";
    }

}
