package com.revature.stay.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(nullable = false)
    private int totalGuests;

    @Column(nullable = false)
    private Date checkIn;

    @Column(nullable = false)
    private Date checkOut;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private float total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status = ReservationStatus.PENDING;

    public Reservation() {
    }

    public Reservation(int reservationId, User user, Room room, int totalGuests, Date checkIn, Date checkOut, Date createdAt, float total, ReservationStatus reservationStatus) {
        this.reservationId = reservationId;
        this.user = user;
        this.room = room;
        this.totalGuests = totalGuests;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.createdAt = createdAt;
        this.total = total;
        this.status = reservationStatus;
    }

    public Reservation(User user, Room room, int totalGuests, Date checkIn, Date checkOut) {
        this.user = user;
        this.room = room;
        this.totalGuests = totalGuests;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
//        this.status = ReservationStatus.PENDING;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getTotalGuests() {
        return totalGuests;
    }

    public void setTotalGuests(int totalGuests) {
        this.totalGuests = totalGuests;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public ReservationStatus getReservationStatus() {
        return status;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.status = reservationStatus;
    }
}
