package com.michalmlynarczyk.bikeservicemanagementservice.controller;

import org.springframework.http.ResponseEntity;

public class OrderControllerImpl implements OrderController {

    @Override
    public ResponseEntity<?> getTest() {
        return ResponseEntity.ok("hello");
    }

}
