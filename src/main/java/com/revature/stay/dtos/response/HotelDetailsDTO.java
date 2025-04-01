package com.revature.stay.dtos.response;

import com.revature.stay.models.Hotel;
import com.revature.stay.models.HotelAmenity;
import com.revature.stay.models.HotelImage;

import java.util.List;

public class HotelDetailsDTO {

    private Hotel hotel;
    private List<HotelImage> images;
    private List<HotelAmenity> amenities;


    public HotelDetailsDTO() {
    }

    public HotelDetailsDTO(Hotel hotel, List<HotelImage> images, List<HotelAmenity> amenities) {
        this.hotel = hotel;
        this.images = images;
        this.amenities = amenities;
    }

    // Getters y Setters

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<HotelImage> getImages() {
        return images;
    }

    public void setImages(List<HotelImage> images) {
        this.images = images;
    }

    public List<HotelAmenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<HotelAmenity> amenities) {
        this.amenities = amenities;
    }
}