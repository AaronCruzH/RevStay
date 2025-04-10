package com.revature.stay.dto.request;

import com.revature.stay.models.RoomType;

public class RoomFilterDTO {

    private int hotelId;
    private String name;
    private String country;
    private String state;
    private String city;
    private int roomId;
    private RoomType roomType;
    private int capacity;
    private float priceMin;
    private float priceMax;
    private String checkIn;
    private String checkOut;

    public RoomFilterDTO(){}

    public RoomFilterDTO(int hotelId, String name, String country, String state, String city, int roomId, RoomType roomType, int capacity, float priceMin, float priceMax, String checkIn, String checkOut) {
        this.hotelId = hotelId;
        this.name = name;
        this.country = country;
        this.state = state;
        this.city = city;
        this.roomId = roomId;
        this.roomType = roomType;
        this.capacity = capacity;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(float priceMin) {
        this.priceMin = priceMin;
    }

    public float getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(float priceMax) {
        this.priceMax = priceMax;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
