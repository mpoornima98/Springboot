package com.mpmd.mi.event.consumer.service;
import com.mpmd.mi.event.consumer.model.EventDetails;
import com.mpmd.mi.event.consumer.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public void addEvent(EventDetails eventDetails) throws IOException {
        eventRepository.addEvent(eventDetails);
    }
}
