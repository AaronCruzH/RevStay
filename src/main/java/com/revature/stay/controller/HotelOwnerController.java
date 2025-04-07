package com.revature.stay.controller;

import com.revature.stay.models.User;
import com.revature.stay.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/hotel-owners")
public class HotelOwnerController {

    private final UserService userService;

    public HotelOwnerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllHotelOwners() {
        return ResponseEntity.ok(userService.getUsersByRole("OWNER"));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('OWNER') and #id == authentication.principal.id)")
    public ResponseEntity<User> getHotelOwnerById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (!"OWNER".equals(user.getRole())) {
            throw new RuntimeException("User is not a hotel owner");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('OWNER') and #id == authentication.principal.id)")
    public ResponseEntity<User> updateHotelOwner(@PathVariable Long id, @RequestBody User ownerDetails) {
        User user = userService.getUserById(id);
        if (!"OWNER".equals(user.getRole())) {
            throw new RuntimeException("User is not a hotel owner");
        }
        ownerDetails.setRole("OWNER"); // Ensure the role doesn't change
        return ResponseEntity.ok(userService.updateUser(id, ownerDetails));
    }
}