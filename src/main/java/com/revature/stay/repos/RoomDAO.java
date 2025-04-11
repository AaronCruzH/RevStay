package com.revature.stay.repos;

import com.revature.stay.models.Room;
import com.revature.stay.models.RoomStatus;
import org.hibernate.sql.Update;
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
    
    @Query("SELECT r FROM Room r " +
            "JOIN FETCH r.hotel h " +
            "WHERE (:hotelId IS NULL OR h.hotelId = :hotelId) " +
            "AND (:name IS NULL OR h.name ILIKE %:name%) " +
            "AND (:country IS NULL OR h.country ILIKE %:country%) " +
            "AND (:state IS NULL OR h.state ILIKE %:state%) " +
            "AND (:city IS NULL OR h.city ILIKE %:city%) " +
            "AND (:roomId IS NULL OR r.roomID = :roomId) " +
            "AND (:roomTypeId IS NULL OR r.roomType = :roomTypeId) " +
            "AND (:capacity IS NULL OR r.capacity >= :capacity) " +
            "AND (:priceMin IS NULL OR r.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR r.price <= :priceMax) " +
            "AND NOT EXISTS (" +
            "   SELECT 1 FROM Reservation re " +
            "   WHERE re.room.roomID = r.roomID " +
            "   AND re.status IN ('ACCEPTED') " +
            "   AND NOT (re.checkOut <= :checkIn OR re.checkIn >= :checkOut)" +
            ") ORDER BY h.name ASC")
    List<Room> findAvailableRooms(
            @Param("hotelId") Integer hotelId,
            @Param("name") String name,
            @Param("country") String country,
            @Param("state") String state,
            @Param("city") String city,
            @Param("roomId") Integer roomId,
            @Param("roomType") String roomType,
            @Param("capacity") Integer capacity,
            @Param("priceMin") Float priceMin,
            @Param("priceMax") Float priceMax,
            @Param("checkIn") Date checkIn,
            @Param("checkOut") Date checkOut
    );

    /*@Query(value = "UPDATE rooms SET status = %:newStatus% WHERE roomid = %:roomId% RETURNING *",nativeQuery = true)
    //@Query("UPDATE Room r SET r.status = :newStatus WHERE r.roomId = :roomId RETURNING r")
    Room updateRoomStatus(@Param("roomId") int roomId, @Param("newStatus")String newStatus);*/
}