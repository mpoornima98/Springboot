package com.mpmd.mi.event.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mpmd.mi.event.consumer.model.EventDetails;

import java.io.IOException;

public interface EventService {
    public void addEvent(EventDetails eventDetails) throws IOException;
}
