package com.mpmd.mi.event.consumer.model;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private Object data;
    private boolean success;

    public ApiResponse(String message, Object data, boolean success) {
        this.data= data;
        this.message = message;
        this.success = success;
    }
}
