package com.revature.stay.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 30)
    private String Country;

    @Column(nullable = false, length = 30)
    private String State;

    @Column(nullable = false, length = 30)
    private String City;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false, length = 10)
    private String houseNumber;

    @Column(nullable = false, length = 10)
    private String postalCode;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelAmenity> amenities;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelImage> images;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomInventory> roomInventories;

    public Hotel() {

    }

    public Hotel(int hotelId, String name, String country, String state, String city, String street, String houseNumber, String postalCode, List<HotelAmenity> amenities, List<HotelImage> images) {
        this.hotelId = hotelId;
        this.name = name;
        Country = country;
        State = state;
        City = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.amenities = amenities;
        this.images = images;
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

    public List<HotelAmenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<HotelAmenity> amenities) {
        this.amenities = amenities;
    }

    public List<HotelImage> getImages() {
        return images;
    }

    public void setImages(List<HotelImage> images) {
        this.images = images;
    }
}
