package com.mpmd.missionPossible.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mission")
public class MissionController {

    @GetMapping("/type")
    public String type(){
        return "WELCOME";
    }
}

