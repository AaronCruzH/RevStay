package com.revature.stay.repos;

import com.revature.stay.models.HotelAmenity;
import com.revature.stay.models.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelImageDAO extends JpaRepository<HotelImage, Integer> {
    @Query("SELECT hi FROM HotelImage hi WHERE hi.hotel.hotelId = :hotelId")
    List<HotelImage> findByHotelId(@Param("hotelId") int hotelId);
}
