package com.revature.stay.services;


import com.revature.stay.dto.request.HotelOwnerRegistrationDto;
import com.revature.stay.models.User;

import java.util.List;

public interface HotelOwnerService {
    User getHotelOwnerById(Long id);
    User getHotelOwnerByEmail(String email);
    List<User> getAllHotelOwners();
    User updateHotelOwner(Long id, HotelOwnerRegistrationDto ownerDetails);
    void deleteHotelOwner(Long id);

    // Hotel-specific operations
    void addHotel(Long ownerId, Long hotelId);
    void removeHotel(Long ownerId, Long hotelId);
    List<Long> getOwnedHotels(Long ownerId);
}