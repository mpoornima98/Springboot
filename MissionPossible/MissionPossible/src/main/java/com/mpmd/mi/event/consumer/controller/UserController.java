package com.mpmd.mi.event.consumer.controller;

import com.mpmd.mi.event.consumer.model.UserData;
import com.mpmd.mi.event.consumer.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserData userData){
        userService.addUser(userData);
        return ResponseEntity.ok("User added successfully");
    }

}
