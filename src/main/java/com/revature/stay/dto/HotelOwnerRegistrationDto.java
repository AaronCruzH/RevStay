package com.revature.stay.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelOwnerRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    // These fields will be stored in a separate hotel_details table
    private String businessName;
    private String businessAddress;
    private String phoneNumber;
}
