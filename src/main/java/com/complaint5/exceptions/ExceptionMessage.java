package com.complaint5.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ExceptionMessage {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
}
