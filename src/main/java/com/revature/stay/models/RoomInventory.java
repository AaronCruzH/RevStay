package com.revature.stay.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "room_inventory")
public class RoomInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomInventoryID;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(nullable = false)
    private int availableRooms;
}