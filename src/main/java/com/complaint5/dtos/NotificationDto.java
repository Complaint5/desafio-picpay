package com.complaint5.dtos;

public record NotificationDto(
        AccountDto accountDto,
        String message) {
}
