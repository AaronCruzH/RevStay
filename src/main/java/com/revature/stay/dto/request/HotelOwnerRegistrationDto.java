package com.revature.stay.dto.request;


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
    private String businessName;
    private String businessAddress;
    private String phoneNumber;
}
