package com.michalmlynarczyk.bikeservicemanagementservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerImpl implements OrderController {

    @Override
    public ResponseEntity<?> getTest() {
        return ResponseEntity.ok("hello");
    }

}