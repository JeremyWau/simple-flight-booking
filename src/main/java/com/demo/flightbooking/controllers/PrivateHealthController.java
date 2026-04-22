package com.demo.flightbooking.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/health")
public class PrivateHealthController {
    @GetMapping("")
    public String health() {
        return "Service private alive";
    }
}
