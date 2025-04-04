package com.revature.stay.exceptions;

public class StayAlreadyReservedException extends RuntimeException {
    public StayAlreadyReservedException(String message) {
        super(message);
    }
}
