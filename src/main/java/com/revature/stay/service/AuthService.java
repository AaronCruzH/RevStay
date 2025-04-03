package com.revature.stay.service;


import com.revature.stay.dto.HotelOwnerRegistrationDto;
import com.revature.stay.dto.JwtAuthResponse;
import com.revature.stay.dto.LoginDto;
import com.revature.stay.dto.UserRegistrationDto;

public interface AuthService {
    JwtAuthResponse login(LoginDto loginDto);
    String registerUser(UserRegistrationDto registrationDto);
    String registerHotelOwner(HotelOwnerRegistrationDto registrationDto);
}