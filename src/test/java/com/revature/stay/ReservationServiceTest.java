package com.revature.stay;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationDAO mockedReservationDAO;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation mockReservation;

    @BeforeEach
    void setup() {
        mockReservation = new Reservation(
            1,
            1,
            1,
            2,
            Date.from(LocalDate.now()
                    .plusDays(3)
                    .atTime(12, 0)
                    .atZone(ZoneId.systemDefault())
                    .toInstant()),
            Date.from(LocalDate.now()
                    .plusDays(10)
                    .atTime(12, 0)
                    .atZone(ZoneId.systemDefault())
                    .toInstant()),
            Date.from(Instant.now()),
            700,
            ReservationStatus.PENDING
        );
    }

    // --- User Management
    // 1.5 * TODO unsuccessful reservation due to unavailable stay
    @Test
    public void stayAlreadyReservedShouldThrowException() {
        Reservation reservationToBeRegistered = new Reservation(
                2,  // userId
                2,  // totalGuests
                Date.from(LocalDate.now()  // checkIn
                        .plusDays(3)
                        .atTime(12, 0)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()),
                Date.from(LocalDate.now()  // checkOut
                        .plusDays(10)
                        .atTime(12, 0)
                        .atZone(ZoneId.systemDefault())
                        .toInstant())
        );

        when(reservationService.checkStayAvailability(
            reservationToBeRegistered.getTotalGuests(),
            reservationToBeRegistered.getCheckIn(),
            reservationToBeRegistered.getCheckOut()
        )).thenReturn(false);

        assertThrows(StayAlreadyReservedException.class, () -> reservationService.createReservation(mockReservation));
    }

    @Test
    public void excessiveGuestsShouldReturnException() {
        Reservation reservationToBeRegistered = new Reservation(
                1,  // userId
                9,  // totalGuests
                Date.from(LocalDate.now()  // checkIn
                        .plusDays(3)
                        .atTime(12, 0)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()),
                Date.from(LocalDate.now()  // checkOut
                        .plusDays(10)
                        .atTime(12, 0)
                        .atZone(ZoneId.systemDefault())
                        .toInstant())
        );

        when(reservationService.checkStayAvailability(
                reservationToBeRegistered.getTotalGuests(),
                reservationToBeRegistered.getCheckIn(),
                reservationToBeRegistered.getCheckOut()
        )).thenReturn(false);

        assertThrows(ExcessiveTotalGuestsException.class, () -> reservationService.createReservation(mockReservation));
    }

    // 1.5 * TODO successful reservation due to available stay
    @Test
    public void availableStayShouldReturnReservation() {
        Reservation reservationToBeRegistered = new Reservation(
                2,  // userId
                2,  // totalGuests
                Date.from(LocalDate.now()  // checkIn
                        .plusDays(12)
                        .atTime(12, 0)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()),
                Date.from(LocalDate.now()  // checkOut
                        .plusDays(15)
                        .atTime(12, 0)
                        .atZone(ZoneId.systemDefault())
                        .toInstant())
        );

        when(reservationService.checkStayAvailability(
                reservationToBeRegistered.getTotalGuests(),
                reservationToBeRegistered.getCheckIn(),
                reservationToBeRegistered.getCheckOut()
        )).thenReturn(true);

        Reservation returnedReservation = reservationService.createReservation(reservationToBeRegistered);

        assertNotNull(returnedReservation);
    }

    // 1.5 * rooms by available date type and number of guests

    // 1.6 * Get reservations by User ID

    // 1.6 * TODO cancel and update reservation

    // 1.7 Email or

    // 1.10 Completed reservation invoice


    // --- Hotel Owner Management
    // 2.4 Send new bookings and reservations updates notifications to hotel owner

    // 2.5 * Get all reservations *

    // 2.5 * Accept a reservation *

    // 2.5 * Reject a reservation *

    // 2.8 Booking statistics
}
