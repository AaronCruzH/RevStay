package com.revature.stay.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomID;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(nullable = false)
    private int capacity;
    @Column(nullable = false)
    private int roomNumber;
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;

    @Enumerated(value = EnumType.STRING)
    private RoomStatus status;

    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", hotel=" + hotel +
                ", capacity=" + capacity +
                ", roomNumber=" + roomNumber +
                ", roomType=" + roomType +
                ", status=" + status +
                '}';
    }

    public Room(int roomID, Hotel hotel, int capacity, int roomNumber, RoomType roomType, RoomStatus status) {
        this.roomID = roomID;
        this.hotel = hotel;
        this.capacity = capacity;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
    }

    public int getRoomID() {
        return roomID;
    }

    public RoomType getType() {
        return roomType;
    }

    public void setType(RoomType type) {
        this.roomType = type;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomStatus getStatus() {
        return status;
    }
}