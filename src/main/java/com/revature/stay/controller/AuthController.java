package com.revature.stay.controller;

import com.revature.stay.dto.request.HotelOwnerRegistrationDto;
import com.revature.stay.dto.request.JwtAuthResponse;
import com.revature.stay.dto.request.LoginDto;
import com.revature.stay.dto.request.UserRegistrationDto;
import com.revature.stay.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        if (registrationDto.getRole() == null) {
            registrationDto.setRole("USER");
        }
        return new ResponseEntity<>(authService.registerUser(registrationDto), HttpStatus.CREATED);
    }

    @PostMapping("/register/hotel-owner")
    public ResponseEntity<String> registerHotelOwner(@RequestBody HotelOwnerRegistrationDto registrationDto) {
        return new ResponseEntity<>(authService.registerHotelOwner(registrationDto), HttpStatus.CREATED);
    }

    @GetMapping("/check")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> checkCurrentUser(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.ok("No authentication found");
        }
        Map<String, Object> details = new HashMap<>();
        details.put("name", authentication.getName());
        details.put("authorities", authentication.getAuthorities());
        details.put("principal", authentication.getPrincipal());
        details.put("isAuthenticated", authentication.isAuthenticated());

        return ResponseEntity.ok(details);
    }

}