package com.revature.stay.models;


import jakarta.persistence.*;

@Entity
@Table(name = "hotel_images")
public class HotelImage {
//    image_id serial primary key,
//    hotel_id int references hotels,
//    name varchar(40) not null,
//    description varchar(255) not null,
//    url varchar(255) not null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelImageId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name ="hotel_id")
    private Hotel hotel;

    public HotelImage(){

    }

    public HotelImage(String name, String description, String url, Hotel hotel) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.hotel = hotel;
    }

    public int getHotelImageId() {
        return hotelImageId;
    }

    public void setHotelImageId(int hotelImageId) {
        this.hotelImageId = hotelImageId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
