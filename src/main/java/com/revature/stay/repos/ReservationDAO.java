package com.revature.stay.repos;

import com.revature.stay.models.Reservation;
import com.revature.stay.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation, Integer> {
//    @Query()
//    boolean checkStayAvailability(int hotelId, RoomType roomType, int totalGuests, Date checkIn, Date checkOut);

    List<Reservation> findByUserId(Long userId);

    @Query(value = "SELECT r.* FROM reservations r " +
            "JOIN rooms r2 ON r.room_id = r2.roomid " +
            "JOIN hotels h ON r2.hotel_id = h.hotel_id " +
            "WHERE h.user_id = :userId",
            nativeQuery = true)
    List<Reservation> findByHotelOwnerId(@Param("userId") Long userId);
}
