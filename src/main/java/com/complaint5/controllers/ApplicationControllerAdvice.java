package com.complaint5.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.complaint5.exceptions.DeclinedTransactionException;
import com.complaint5.exceptions.ExceptionMessage;
import com.complaint5.exceptions.InsufficientFundsException;
import com.complaint5.exceptions.NotAutorizedException;
import com.complaint5.exceptions.RecordNotFondException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    
    @ExceptionHandler(RecordNotFondException.class)
    public ResponseEntity<ExceptionMessage> handlerRecordNotFondException(RecordNotFondException exception){
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error(HttpStatus.NOT_FOUND.name())
                        .message(exception.getMessage())
                        .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ExceptionMessage> handlerInsufficientFundsException(InsufficientFundsException exception){
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.name())
                        .message(exception.getMessage())
                        .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAutorizedException.class)
    public ResponseEntity<ExceptionMessage> handlerNotAutorizedException(NotAutorizedException exception){
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.FAILED_DEPENDENCY.value())
                        .error(HttpStatus.FAILED_DEPENDENCY.name())
                        .message(exception.getMessage())
                        .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.FAILED_DEPENDENCY);
    }

    @ExceptionHandler(DeclinedTransactionException.class)
    public ResponseEntity<ExceptionMessage> DeclinedTransactionException(DeclinedTransactionException exception){
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.FORBIDDEN.value())
                        .error(HttpStatus.FORBIDDEN.name())
                        .message(exception.getMessage())
                        .build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.FORBIDDEN);
    }
}
