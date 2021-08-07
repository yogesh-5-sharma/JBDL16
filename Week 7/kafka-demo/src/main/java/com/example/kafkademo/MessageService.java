package com.example.kafkademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private static final String TOPIC = "JBDL16-KAFKA-DEMO2";

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message, String key) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Produced Message : " + message + " with key : " + key);
    }

    @KafkaListener(topics = TOPIC, groupId = "JBDL16-KAFKA-GROUP")
    public void consumeMessage(String message) {
        System.out.println("Consumed Message from consumer1 : " + message);
    }

    @KafkaListener(topics = TOPIC, groupId = "JBDL16-KAFKA-GROUP2")
    public void consumeMessage2(String message) {
        System.out.println("Consumed Message from consumer2 : " + message);
    }
}

//KafkaConsumer@917d110 partition1
//KafkaConsumer@1b20c10c partition2
//KafkaConsumer@917d110 partition0