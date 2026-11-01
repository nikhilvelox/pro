package com.velox.notificationservice.dto;


public record MessageRequest(
        String message,
        String key
) {
}