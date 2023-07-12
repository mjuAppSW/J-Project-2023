package com.mjuAppSW.appName.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Room")
public class RoomApiController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/createRoom")
    public HttpStatus createRoom(){
        roomService.createRoom();
        return HttpStatus.OK;
    }
}