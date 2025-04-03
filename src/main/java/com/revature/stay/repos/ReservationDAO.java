package com.revature.stay.repos;

import com.revature.stay.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDAO extends JpaRepository<Reservation, Integer> {

}
