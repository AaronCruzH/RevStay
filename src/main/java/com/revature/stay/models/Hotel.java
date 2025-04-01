package com.revature.stay.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelID;

    @ManyToMany
    @JoinTable(
            name = "room_inventory",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private Set<Room> rooms;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private float priceRange;
    @Column(nullable = false)
    private String amenities;

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPriceRange(float priceRange) {
        this.priceRange = priceRange;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public float getPriceRange() {
        return priceRange;
    }

    public String getAmenities() {
        return amenities;
    }

    public Hotel(int hotelID, Set<Room> rooms, String name, String location, float priceRange, String amenities) {
        this.hotelID = hotelID;
        this.rooms = rooms;
        this.name = name;
        this.location = location;
        this.priceRange = priceRange;
        this.amenities = amenities;
    }

    public Hotel() {
    }
}
