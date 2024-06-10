package com.mpmd.mi.event.consumer.exception;


public class EmptyEventException extends RuntimeException {
    public EmptyEventException(String message){
        super(message);
    }
}
