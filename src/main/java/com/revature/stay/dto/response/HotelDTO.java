package com.revature.stay.dto.response;

import com.revature.stay.models.Hotel;

public class HotelDTO {
    private int hotelId;

    private String name;

    private String Country;

    private String State;

    private String City;

    private String street;

    private String houseNumber;

    private String postalCode;

    public HotelDTO(){

    }

    public HotelDTO(Hotel hotel){
        this.hotelId=hotel.getHotelId();
        this.name=hotel.getName();
        this.Country=hotel.getCountry();
        this.State=hotel.getState();
        this.City=hotel.getCity();
        this.street=hotel.getStreet();
        this.houseNumber= hotel.getHouseNumber();
        this.postalCode=hotel.getPostalCode();
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
