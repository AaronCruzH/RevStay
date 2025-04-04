package com.revature.stay.services;

import com.revature.stay.models.Reservation;
import com.revature.stay.models.ReservationStatus;
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

    public Reservation updateReservation(Reservation reservation) {
        return reservationDAO.save(reservation);
    }

    public Reservation cancelReservation(Reservation reservation) {
        reservation.setReservationStatus(ReservationStatus.CANCELED);
        return reservationDAO.save(reservation);
    }

    public Reservation rejectReservation(Reservation reservation) {
        reservation.setReservationStatus(ReservationStatus.REJECTED);
        return reservationDAO.save(reservation);
    }

    public Reservation acceptReservation(Reservation reservation) {
        reservation.setReservationStatus(ReservationStatus.ACCEPTED);
        return reservationDAO.save(reservation);
    }
}
