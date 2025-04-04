package com.revature.stay.services;

import com.revature.stay.models.Reservation;
import com.revature.stay.repos.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationDAO reservationDAO;

    @Autowired
    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationDAO.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationDAO.findAll();
    }
}
