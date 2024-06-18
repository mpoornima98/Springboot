package com.mpmd.mi.event.consumer.repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpmd.mi.event.consumer.controller.EventController;
import com.mpmd.mi.event.consumer.exception.InValidInputException;
import com.mpmd.mi.event.consumer.model.EventDetails;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EventController.class);
    @Value("${application.eventFile}")
    private String eventFile;
    ObjectMapper objectMapper = new ObjectMapper();


    public void addEvent(EventDetails eventDetail) throws IOException {
        List<EventDetails> eventDetailsList = new ArrayList<EventDetails>();
        if (!isFileEmpty()){
            eventDetailsList = events();
            for (EventDetails event : eventDetailsList) {
                if(eventDetail.getEventID().equals(event.getEventID())){
                    throw new InValidInputException("Existing user");
                }
            }
        }
        eventDetailsList.add(eventDetail);
        logger.info("Writing the value to the file");
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(eventFile),eventDetailsList);
    }

    private List<EventDetails> events() throws IOException {
        return objectMapper.readValue(new File(eventFile)
              , new TypeReference<List<EventDetails>>(){});
    }

    private boolean isFileEmpty() throws IOException {
        if(Files.size(Paths.get(eventFile))==0){
            return true;

        }
        return false;
    }
}

