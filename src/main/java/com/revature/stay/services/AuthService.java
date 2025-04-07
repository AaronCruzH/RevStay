package com.revature.stay.services;


import com.revature.stay.dto.request.HotelOwnerRegistrationDto;
import com.revature.stay.dto.request.JwtAuthResponse;
import com.revature.stay.dto.request.LoginDto;
import com.revature.stay.dto.request.UserRegistrationDto;

public interface AuthService {
    JwtAuthResponse login(LoginDto loginDto);
    String registerUser(UserRegistrationDto registrationDto);
    String registerHotelOwner(HotelOwnerRegistrationDto registrationDto);
}