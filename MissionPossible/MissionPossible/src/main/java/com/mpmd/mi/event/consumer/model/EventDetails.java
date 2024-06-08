package com.mpmd.mi.event.consumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class EventDetails {
    @JsonProperty("Event_Name")
    String EventName;
    @JsonProperty("Event_ID")
    String EventID;

   // List<SessionDetails> sessionDetails;
}





