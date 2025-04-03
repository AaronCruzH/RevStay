package com.revature.stay.controller;

import com.revature.stay.dto.HotelOwnerRegistrationDto;
import com.revature.stay.dto.JwtAuthResponse;
import com.revature.stay.dto.LoginDto;
import com.revature.stay.dto.UserRegistrationDto;
import com.revature.stay.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    // Register regular user endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        // Ensure role is set to USER
        if (registrationDto.getRole() == null) {
            registrationDto.setRole("USER");
        }
        return new ResponseEntity<>(authService.registerUser(registrationDto), HttpStatus.CREATED);
    }

    // Register hotel owner endpoint
    @PostMapping("/register/hotel-owner")
    public ResponseEntity<String> registerHotelOwner(@RequestBody HotelOwnerRegistrationDto registrationDto) {
        return new ResponseEntity<>(authService.registerHotelOwner(registrationDto), HttpStatus.CREATED);
    }
}