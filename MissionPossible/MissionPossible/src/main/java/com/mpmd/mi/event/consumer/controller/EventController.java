package com.mpmd.mi.event.consumer.controller;


import com.mpmd.mi.event.consumer.exception.EmptyEventException;
import com.mpmd.mi.event.consumer.exception.InValidInputException;
import com.mpmd.mi.event.consumer.model.EventDetails;
import com.mpmd.mi.event.consumer.service.EventServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/event")
public class EventController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EventController.class);
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
        if(eventDetail.getEventID()==null || eventDetail.getEventName() == null) {
            logger.error("Either Event ID or Name is not provided",
                    new InValidInputException("Either Event ID or Name is not provided"));
            return ResponseEntity.badRequest().body("Either Event ID or Name is not provided");
        }

        eventService.addEvent(eventDetail);
        logger.debug("fgf");
        logger.info("Event added successfully");
        return ResponseEntity.ok("Added successfully");

    }

}

