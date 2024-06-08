package com.mpmd.mi.event.consumer.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<String> handleNullValueException(NullValueException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
