package com.demo.flightbooking.controllers;

import com.demo.flightbooking.dtos.UserDto;
import com.demo.flightbooking.entities.UserSimplified;
import com.demo.flightbooking.services.security.JwtService;
import com.demo.flightbooking.services.security.UserSimplifiedDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserSimplifiedDetailsService userSimplifiedDetailsService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto user) {
        UserDto createdUser = userSimplifiedDetailsService.createUser(user);
        return ResponseEntity.ok("Registration successfully, you can now authenticate with your credentials for your account: "+createdUser.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserSimplified user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
}