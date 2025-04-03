package com.revature.stay.config;

import com.revature.stay.controller.AuthController;
import com.revature.stay.controller.HotelOwnerController;
import com.revature.stay.controller.UserController;
import com.revature.stay.service.AuthService;
import com.revature.stay.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ControllerConfig {

    @Bean
    public AuthController authController(AuthService authService) {
        return new AuthController(authService);
    }

    @Bean
    public UserController userController(UserService userService) {
        return new UserController(userService);
    }

    @Bean
    public HotelOwnerController hotelOwnerController(UserService userService) {
        return new HotelOwnerController(userService);
    }
}