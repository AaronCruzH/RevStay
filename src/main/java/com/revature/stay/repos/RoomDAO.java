package com.revature.stay.repos;

import com.revature.stay.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDAO extends JpaRepository<Room,Integer> {

    //@Query(value = "SELECT * FROM rooms WHERE hotel_id = %:hotelId%",nativeQuery = true)
    @Query("SELECT r FROM Room r WHERE r.hotel.hotelId = :hotelID")
    List<Room> getRoomsByHotelId(@Param("hotelID") int hotelId);
}