package com.mpmd.mi.event.consumer.repository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mpmd.mi.event.consumer.model.EventDetails;
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

    @Value("${EventFile}")
    private String eventFile;
    ObjectMapper objectMapper = new ObjectMapper();
    List<EventDetails> eventDetails= new ArrayList<EventDetails>();

    public void addEvent(EventDetails eventDetail) throws IOException {
        eventDetails = events();
        eventDetails.add(eventDetail);
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(eventFile),eventDetails);
    }

    public List<EventDetails> events() throws IOException {
        if(Files.size(Paths.get(eventFile))==0){
            return eventDetails;
        }
      eventDetails = objectMapper.readValue(new File(eventFile)
              , new TypeReference<List<EventDetails>>(){});
        return eventDetails;
    }
}
