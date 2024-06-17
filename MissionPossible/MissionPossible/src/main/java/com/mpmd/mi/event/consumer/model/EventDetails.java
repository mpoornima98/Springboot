package com.mpmd.mi.event.consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class EventDetails {
    @JsonProperty("eventName")
    private String eventName;
    @JsonProperty("eventID")
    private String eventID;
    @Transient
    private List<String> courseMaterials;

   // List<SessionDetails> sessionDetails;
}





