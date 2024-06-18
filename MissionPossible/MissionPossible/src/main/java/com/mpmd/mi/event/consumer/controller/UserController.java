package com.mpmd.mi.event.consumer.controller;

import com.mpmd.mi.event.consumer.exception.InValidInputException;
import com.mpmd.mi.event.consumer.entity.UserData;
import com.mpmd.mi.event.consumer.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserData userData){
        if(ObjectUtils.isEmpty(userData.getUserId()) || ObjectUtils.isEmpty(userData.getUserName())){
            throw new InValidInputException("Please provide the required user ID and Name");
        }
        userService.addUser(userData);
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserData> getUser(@PathVariable("id") String userID){
        if(ObjectUtils.isEmpty(userID)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(userService.getUser(userID));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userID){
        if(ObjectUtils.isEmpty(userID)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body("User "+userService.deleteUser(userID)+ " deleted successfully");
    }

}
