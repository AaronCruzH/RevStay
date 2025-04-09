package com.revature.stay.services;

import com.revature.stay.models.Reservation;
import com.revature.stay.models.ReservationStatus;
import com.revature.stay.repos.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Reservation> updateReservation(Reservation updateReservation) {
        return reservationDAO.findById(updateReservation.getReservationId()).map(reservation -> {
            reservation.setTotalGuests(updateReservation.getTotalGuests());
            reservation.setCheckIn(updateReservation.getCheckIn());
            reservation.setCheckOut(updateReservation.getCheckOut());
            return reservationDAO.save(reservation);
        });
    }

    public Optional<Reservation> cancelReservation(int reservationId) {
        return reservationDAO.findById(reservationId).map(reservation -> {
            reservation.setReservationStatus(ReservationStatus.CANCELED);
            return reservationDAO.save(reservation);
        });
    }

    public Optional<Reservation> rejectReservation(int reservationId) {
        return reservationDAO.findById(reservationId).map(reservation -> {
            reservation.setReservationStatus(ReservationStatus.REJECTED);
            return reservationDAO.save(reservation);
        });
    }

    public Optional<Reservation> acceptReservation(int reservationId) {
        return reservationDAO.findById(reservationId).map(reservation -> {
            reservation.setReservationStatus(ReservationStatus.ACCEPTED);
            return reservationDAO.save(reservation);
        });
    }
}
