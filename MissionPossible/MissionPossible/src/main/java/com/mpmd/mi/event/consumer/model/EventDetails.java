package com.mpmd.mi.event.consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class EventDetails {
    @JsonProperty("eventName")
    private String eventName;
    @JsonProperty("eventID")
    private String eventID;

   // List<SessionDetails> sessionDetails;
}





