package com.revature.stay.repos;

import com.revature.stay.models.Reservation;
import com.revature.stay.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ReservationDAO extends JpaRepository<Reservation, Integer> {
//    @Query()
//    boolean checkStayAvailability(int hotelId, RoomType roomType, int totalGuests, Date checkIn, Date checkOut);
}
