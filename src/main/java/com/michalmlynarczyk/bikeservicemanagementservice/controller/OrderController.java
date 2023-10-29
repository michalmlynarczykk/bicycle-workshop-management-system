package com.michalmlynarczyk.bikeservicemanagementservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/external/v1/order")
public interface OrderController {


    @GetMapping("/test")
    ResponseEntity<?> getTest();
}
