package com.revature.stay.dto.response;

import com.revature.stay.models.Room;
import com.revature.stay.models.RoomType;

public class RoomWithDetailsDTO {
    private int roomID;
    private int capacity;
    private int roomNumber;
    private float price;
    private RoomType roomType;
    private HotelDTO hotel;

    public RoomWithDetailsDTO(){}

    public RoomWithDetailsDTO(Room room) {
        this.roomID = room.getRoomID();
        this.capacity=room.getCapacity();
        this.roomNumber=room.getRoomNumber();
        this.price= room.getPrice();
        this.roomType=room.getRoomType();
        this.hotel = new HotelDTO(room.getHotel());
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public HotelDTO getHotel() {
        return hotel;
    }

    public void setHotel(HotelDTO hotel) {
        this.hotel = hotel;
    }
}
