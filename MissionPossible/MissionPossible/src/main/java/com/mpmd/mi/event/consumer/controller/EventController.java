package com.mpmd.mi.event.consumer.controller;


import com.mpmd.mi.event.consumer.exception.EmptyEventException;
import com.mpmd.mi.event.consumer.exception.NoEventNameOrIdException;
import com.mpmd.mi.event.consumer.model.EventDetails;
import com.mpmd.mi.event.consumer.service.EventServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/event")
public class EventController {

    static final Logger logger = LogManager.getLogger(EventController.class);
    @Autowired
    EventServiceImpl eventService;

    @GetMapping("/type")
    public String type(){
        return "WELCOME";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEvent(@RequestBody EventDetails eventDetail) throws IOException {
        if(ObjectUtils.isEmpty(eventDetail)){
            logger.error("Event is not provided");
            throw new EmptyEventException("Please provide Event details");
        }
        if(eventDetail.getEventID()==null || eventDetail.getEventName() == null){
            logger.error("Either Event ID or Name is not provided");
            throw new NoEventNameOrIdException("Either Event ID or Name is not provided");
        }

        eventService.addEvent(eventDetail);
        logger.info("Event added successfully");
        return ResponseEntity.ok("Added successfully");

    }

}

