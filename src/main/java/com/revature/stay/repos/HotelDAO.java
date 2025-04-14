package com.revature.stay.repos;

import com.revature.stay.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelDAO extends JpaRepository<Hotel, Integer> {

    @Query("SELECT h FROM Hotel h WHERE " +
            "(:name is null or h.name ILIKE %:name%) AND " +
            "(:country is null or h.country ILIKE :country) AND " +
            "(:state is null or h.state ILIKE :state) AND " +
            "(:city is null or h.city ILIKE :city)")
    List<Hotel> findHotelsByFilter(
            @Param("name") String name,
            @Param("country") String country,
            @Param("state") String state,
            @Param("city") String city
    );

}
