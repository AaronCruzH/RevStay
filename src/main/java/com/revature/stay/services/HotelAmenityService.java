package com.revature.stay.services;

import com.revature.stay.models.Hotel;
import com.revature.stay.models.HotelAmenity;
import com.revature.stay.repos.HotelAmenityDAO;
import com.revature.stay.repos.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelAmenityService {


    private final HotelAmenityDAO hotelAmenityDAO;
    private final HotelDAO hotelDAO;

    @Autowired
    public HotelAmenityService(HotelAmenityDAO hotelImageDAO,HotelDAO hotelDAO){
        this.hotelAmenityDAO=hotelImageDAO;
        this.hotelDAO=hotelDAO;
    }

    public boolean checkHotelExisting(int hotelId){
        return hotelDAO.findById(hotelId).isEmpty();
    }


    public boolean checkHotelAmenityExisting(int hotelAmenityId){
        return hotelAmenityDAO.findById(hotelAmenityId).isEmpty();
    }


    public boolean checkHotelRelationWithAmenity(int hotelId, int hotelAmenityId){
        return hotelAmenityDAO.findByHotelAmenityIdAndHotel_HotelId(hotelAmenityId, hotelId).isEmpty();
    }


    // CREATE
    public HotelAmenity createHotelAmenity(int hotelId, HotelAmenity hotelAmenityToBeCreated) {
        Optional<Hotel> hotelOptional = hotelDAO.findById(hotelId);
        if (hotelOptional.isEmpty()) {
            return null;
        }
        Hotel hotel = hotelOptional.get();
        hotelAmenityToBeCreated.setHotel(hotel);
        return hotelAmenityDAO.save(hotelAmenityToBeCreated);
    }

    // RETRIEVE BY ID
    public Optional<HotelAmenity> getHotelAmenityById(int hotelId, int hotelAmenityId){
        return hotelAmenityDAO.findByHotelAmenityIdAndHotel_HotelId(hotelAmenityId, hotelId);
    }

    // RETRIEVE BY HOTEL
    public List<HotelAmenity> getHotelAmenitiesByHotel(int hotelId){
        return hotelAmenityDAO.findByHotelId(hotelId);
    }

    // PUT
    public Optional<HotelAmenity> updateHotelAmenity(int hotelId,int hotelAmenityId, HotelAmenity updateHotelAmenity) {
        return hotelAmenityDAO.findByHotelAmenityIdAndHotel_HotelId(hotelAmenityId,hotelId).map(hotelAmenity -> {
            hotelAmenity.setName(updateHotelAmenity.getName());
            hotelAmenity.setDescription(updateHotelAmenity.getDescription());
            hotelAmenity.setUrl(updateHotelAmenity.getUrl());
            return hotelAmenityDAO.save(hotelAmenity);
        });
    }

    // DELETE
    public void deleteHotelAmenity(int hotelAmenityId) {
        hotelAmenityDAO.deleteById(hotelAmenityId);
    }
}
