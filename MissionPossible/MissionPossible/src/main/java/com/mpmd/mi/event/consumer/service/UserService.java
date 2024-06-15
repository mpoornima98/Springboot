package com.mpmd.mi.event.consumer.service;

import com.mpmd.mi.event.consumer.model.UserData;

public interface UserService {
    public void addUser(UserData userData);
    public UserData getUser(String userID);
    public String deleteUser(String userID);
}
