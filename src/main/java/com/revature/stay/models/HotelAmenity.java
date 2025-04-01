package com.revature.stay.models;

import jakarta.persistence.*;

@Entity
@Table(name = "hotel_amenities")
public class HotelAmenity {

//    amenity_id serial primary key,
//    hotel_id int references hotels,
//    name varchar(40) not null,
//    description varchar(255) not null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int amenityId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public HotelAmenity(){}

    public HotelAmenity(int amenityId, String name, String description, Hotel hotel) {
        this.amenityId = amenityId;
        this.name = name;
        this.description = description;
        this.hotel = hotel;
    }

    public int getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(int amenityId) {
        this.amenityId = amenityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
