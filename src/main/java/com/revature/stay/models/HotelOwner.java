package com.revature.stay.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class extends the User entity to represent a Hotel Owner.
 * It's not an entity itself, but a DTO class that combines User data with hotel owner specific data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelOwner {

    // User information
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    // Hotel owner specific information
    private String businessName;
    private String businessAddress;
    private String phoneNumber;

    // Constructor that takes a User object
    public HotelOwner(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }

    // Convert to User object
    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setRole("OWNER");
        return user;
    }
}