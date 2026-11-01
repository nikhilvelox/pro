package com.velox.consumer.service;

import com.example.kafkaconsumer.dto.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(topics = "orders", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Order order) {

        System.out.println("Order received from Kafka:");
        System.out.println(order);
    }
}