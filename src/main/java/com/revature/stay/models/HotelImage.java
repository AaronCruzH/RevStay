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
    private int imageId;

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

    public HotelImage(int imageId, String name, String description, String url) {
        this.imageId = imageId;
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
}
