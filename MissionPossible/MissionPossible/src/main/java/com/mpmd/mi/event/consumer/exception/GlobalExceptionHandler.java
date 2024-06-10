package com.mpmd.mi.event.consumer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoEventNameOrIdException.class)
    public ResponseEntity<String> handleNullValueException(NoEventNameOrIdException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EmptyEventException.class)
    public ResponseEntity<String> handleEmptyEventException(EmptyEventException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }}
