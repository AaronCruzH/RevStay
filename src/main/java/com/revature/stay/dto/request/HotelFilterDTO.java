package com.revature.stay.dto.request;

public class HotelFilterDTO {
    private String name;
    private String country;
    private String state;
    private String city;
    private Integer owner;

    public HotelFilterDTO(){}

    public HotelFilterDTO(String name, String country, String state, String city) {
        this.name = name;
        this.country = country;
        this.state = state;
        this.city = city;
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

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
}
