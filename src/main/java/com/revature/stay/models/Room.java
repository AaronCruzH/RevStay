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

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomInventory> roomInventories;

    @Column(nullable = false)
    private RoomType type;

    public Room() {
    }

    public Room(int roomID, RoomType type) {
        this.roomID = roomID;
        this.type = type;
    }

    public int getRoomID() {
        return roomID;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}