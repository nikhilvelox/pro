package com.velox.notificationservice.controller;


import com.velox.notificationservice.dto.MessageRequest;
import com.velox.notificationservice.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final KafkaProducerService producerService;
    public MessageController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<?>> sendMessage(@RequestBody MessageRequest request) {
        if (request.message() == null || request.message().isBlank()) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(Map.of("error", "Message cannot be empty")));
        }
        String key = (request.key() == null || request.key().isBlank()) ? "default-key" : request.key();
        return producerService.sendMessage(key, request.message()).<ResponseEntity<?>>thenApply(result -> {
            return ResponseEntity.ok(Map.of("hoil","hoil"));
        }).exceptionally(exception -> ResponseEntity.internalServerError().body(Map.of("status", "FAILED", "error", "Kafka delivery failed")));
    }
}