package com.revature.stay.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelOwner {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String businessName;
    private String businessAddress;
    private String phoneNumber;

    public HotelOwner(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }

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