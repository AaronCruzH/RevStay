package com.revature.stay.controller;

import com.revature.stay.models.Reservation;
import com.revature.stay.services.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation createReservationHandler(@RequestBody Reservation reservation, HttpSession httpSession) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping
    public List<Reservation> getAllReservationsHandler() {
        return reservationService.getAllReservations();
    }
}
