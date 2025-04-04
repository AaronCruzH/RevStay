package com.revature.stay;

import com.revature.stay.controllers.AuthController;
import com.revature.stay.controllers.HotelOwnerController;
import com.revature.stay.controllers.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@ComponentScan(basePackages = "com.revature")
public class StayApplication {

	// Constructor injection of controllers to create explicit references
	private final AuthController authController;
	private final UserController userController;
	private final HotelOwnerController hotelOwnerController;

	public StayApplication(AuthController authController,
							UserController userController,
							HotelOwnerController hotelOwnerController) {
		this.authController = authController;
		this.userController = userController;
		this.hotelOwnerController = hotelOwnerController;
	}


	public static void main(String[] args) {
		SpringApplication.run(StayApplication.class, args);
	}

}
