package com.mpmd.mi.event.consumer.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
public class SessionDetails {
    String sessionID;
    LocalDateTime sessionStartDate;
    LocalDateTime sessionEndDate;

}
