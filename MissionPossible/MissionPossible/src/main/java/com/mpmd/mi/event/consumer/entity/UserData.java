package com.mpmd.mi.event.consumer.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_data")
@Data
public class UserData {

    @Id
    @Column(name = "user_id")
    String userId;
    @Column(name = "user_name")
    String userName;

}
