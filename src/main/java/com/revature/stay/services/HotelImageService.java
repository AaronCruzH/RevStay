package com.revature.stay.services;

import com.revature.stay.models.Hotel;
import com.revature.stay.models.HotelImage;
import com.revature.stay.repos.HotelDAO;
import com.revature.stay.repos.HotelImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelImageService {

    private final HotelImageDAO hotelImageDAO;
    private final HotelDAO hotelDAO;


    @Autowired
    public HotelImageService(HotelImageDAO hotelImageDAO, HotelDAO hotelDAO){
        this.hotelImageDAO=hotelImageDAO;
        this.hotelDAO=hotelDAO;
    }

    public boolean checkHotelExisting(int hotelId){
        return hotelDAO.findById(hotelId).isEmpty();
    }


    public boolean checkHotelImageExisting(int hotelImageId){
        return hotelImageDAO.findById(hotelImageId).isEmpty();
    }


    public boolean checkHotelRelationWithImage(int hotelId, int hotelImageId){
        return hotelImageDAO.findByHotelImageIdAndHotel_HotelId(hotelImageId, hotelId).isEmpty();
    }

    // CREATE
    public HotelImage createHotelImages(int hotelId, HotelImage hotelImageToBeCreated) {
        Optional<Hotel> hotelOptional = hotelDAO.findById(hotelId);
        if (hotelOptional.isEmpty()) {
            return null;
        }
        Hotel hotel = hotelOptional.get();
        hotelImageToBeCreated.setHotel(hotel);
        return hotelImageDAO.save(hotelImageToBeCreated);
    }

    // RETRIEVE BY ID
    public Optional<HotelImage> getHotelImageById(int hotelId, int hotelImageId){
        return hotelImageDAO.findByHotelImageIdAndHotel_HotelId(hotelImageId, hotelId);
    }

    // RETRIEVE BY HOTEL
    public List<HotelImage> getHotelImagesByHotel(int hotelId){
      return hotelImageDAO.findByHotelId(hotelId);
    }

    // PUT
    public Optional<HotelImage> updateHotelImage(int hotelId,int hotelImageId, HotelImage updateHotelImage) {
        return hotelImageDAO.findByHotelImageIdAndHotel_HotelId(hotelImageId,hotelId).map(hotelImage -> {
           hotelImage.setName(updateHotelImage.getName());
           hotelImage.setDescription(updateHotelImage.getDescription());
           hotelImage.setUrl(updateHotelImage.getUrl());
           return hotelImageDAO.save(hotelImage);
        });
    }

    // DELETE
    public void deleteHotelImage(int hotelImageId) {
        hotelImageDAO.deleteById(hotelImageId);
    }

}
