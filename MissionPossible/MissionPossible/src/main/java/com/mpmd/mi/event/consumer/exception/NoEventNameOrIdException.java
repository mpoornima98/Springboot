package com.mpmd.mi.event.consumer.exception;

public class NoEventNameOrIdException extends RuntimeException{
    public NoEventNameOrIdException(String message){
        super(message);
    }
}
