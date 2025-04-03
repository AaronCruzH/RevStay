package com.revature.stay.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationID;

    private int userID;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false)
    private Date checkInDate;
    @Column(nullable = false)
    private Date checkOutDate;
    @Column(nullable = false)
    private RoomType roomType;
    @Column(nullable = false)
    private int GuestCount;
    @Column(nullable = false)
    private ReservationStatus status;
}
