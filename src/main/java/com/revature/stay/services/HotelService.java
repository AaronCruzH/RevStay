package com.revature.stay.services;


import com.revature.stay.dtos.response.HotelDetailsDTO;
import com.revature.stay.models.Hotel;
import com.revature.stay.models.HotelAmenity;
import com.revature.stay.models.HotelImage;
import com.revature.stay.repos.HotelAmenityDAO;
import com.revature.stay.repos.HotelDAO;
import com.revature.stay.repos.HotelImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelDAO hotelDAO;
    private final HotelImageDAO hotelImageDAO;
    private final HotelAmenityDAO hotelAmenityDAO;

    @Autowired
    public HotelService(HotelDAO hotelDAO, HotelImageDAO hotelImageDAO, HotelAmenityDAO hotelAmenityDAO) {
        this.hotelDAO = hotelDAO;
        this.hotelImageDAO = hotelImageDAO;
        this.hotelAmenityDAO = hotelAmenityDAO;
    }

    // Availability
    public boolean checkHotelExisting(int hotelId){
        return hotelDAO.findById(hotelId).isEmpty();
    }

    // CREATE
    public Hotel createHotel(Hotel hotelTobeCreated){
        return hotelDAO.save(hotelTobeCreated);
    }

    // RETRIEVE ALL
    public List<Hotel> getAllHotels(){
        return hotelDAO.findAll();
    }

    // RETRIEVE BY ID WITH DETAILS

    public HotelDetailsDTO getHotelDetails(int hotelId) {
        Optional<Hotel> optionalHotel = hotelDAO.findById(hotelId);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            List<HotelImage> images = hotelImageDAO.findByHotelId(hotelId);
            List<HotelAmenity> amenities = hotelAmenityDAO.findByHotelId(hotelId);
            return new HotelDetailsDTO(hotel, images, amenities);
        }
        return null;
    }

    // RETRIEVE BY ID

    public Optional<Hotel> getHotelById(int hotelId){
        return hotelDAO.findById(hotelId);
    }

    // PUT
    public Optional<Hotel> updateHotel(int hotelId, Hotel updatedHotel) {
        return hotelDAO.findById(hotelId).map(hotel -> {
            hotel.setName(updatedHotel.getName());
            hotel.setLocation(updatedHotel.getLocation());
            return hotelDAO.save(hotel);
        });
    }

    // DELETE
    public void deleteHotel(int hotelId) {
         hotelDAO.deleteById(hotelId);
    }
}
