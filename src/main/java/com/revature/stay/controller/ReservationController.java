package com.revature.stay.controller;

import com.revature.stay.models.Reservation;
import com.revature.stay.models.Role;
import com.revature.stay.models.User;
import com.revature.stay.services.ReservationService;
import com.revature.stay.services.UserService;
import com.revature.stay.utils.AuthUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @PostMapping
//    @PreAuthorize("#id == authentication.principal.id")
    public Reservation createReservationHandler(@RequestBody Reservation reservation) {
        User currentUser = userService.getUserByEmail(AuthUtil.getCurrentUserEmail());
        reservation.setUser(currentUser);
        return reservationService.createReservation(reservation);
    }

    @GetMapping("users/{id}")
    public List<Reservation> getAllUserReservationsHandler(@PathVariable Long id) {
        return reservationService.getReservationsByUserId(id);
    }

    @GetMapping
    public List<Reservation> getAllUserReservations() {
        User user = userService.getUserByEmail(AuthUtil.getCurrentUserEmail());
        if ("ADMIN". equals(user.getRole()) || "OWNER". equals(user.getRole())) {
            return reservationService.getReservationsByHotelOwnerId(user.getId());
        }
        return reservationService.getReservationsByUserId(user.getId());
    }

//    @GetMapping("owner/{id}")
//    public List<Reservation> getAllHotelReservationsHandler() {
//        return reservationService.getAllReservations();
//    }

    @PutMapping("update")
    public Optional<Reservation> updateReservationHandler(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

    @PutMapping("cancel/{reservationId}")
    public Optional<Reservation> cancelReservationHandler(@PathVariable int reservationId) {
        return reservationService.cancelReservation(reservationId);
    }

    @PutMapping("accept/{reservationId}")
    public Optional<Reservation> acceptReservationHandler(@PathVariable int reservationId) {
        return reservationService.acceptReservation(reservationId);
    }

    @PutMapping("reject/{reservationId}")
    public Optional<Reservation> rejectReservationHandler(@PathVariable int reservationId) {
        return reservationService.rejectReservation(reservationId);
    }
}
