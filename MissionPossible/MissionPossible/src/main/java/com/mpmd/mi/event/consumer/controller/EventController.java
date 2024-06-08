package com.mpmd.mi.event.consumer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mpmd.mi.event.consumer.exception.NullValueException;
import com.mpmd.mi.event.consumer.model.EventDetails;
import com.mpmd.mi.event.consumer.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventServiceImpl eventService;

    @GetMapping("/type")
    public String type(){
        return "WELCOME";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEvent(@RequestBody EventDetails eventDetails) throws IOException {
        if(eventDetails.getEventID()==null || eventDetails.getEventName() == null){
            throw new NullValueException("Either Event ID or Name is not provided");
        }
        eventService.addEvent(eventDetails);
        return ResponseEntity.ok("Added successfully");
    }

}

