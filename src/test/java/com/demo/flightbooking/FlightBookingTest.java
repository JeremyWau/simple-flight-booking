package com.demo.flightbooking;

import com.demo.flightbooking.dtos.BookingDto;
import com.demo.flightbooking.dtos.BookingRequestDto;
import com.demo.flightbooking.exceptions.BookingExceedsCapacityException;
import com.demo.flightbooking.exceptions.InvalidScheduledFlightIdException;
import com.demo.flightbooking.exceptions.InvalidSeatException;
import com.demo.flightbooking.exceptions.MissingScheduledFlightIdException;
import com.demo.flightbooking.services.FlightBookingService;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "/sql-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class FlightBookingTest {
    @Autowired
    private FlightBookingService flightBookingService;

    private UserDetails provideUserDetails(final String username) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public @Nullable String getPassword() {
                return "";
            }

            @Override
            public String getUsername() {
                return username;
            }
        };
    }

    @Test
    void bookingServiceCreateBookingMissingFlightId() {
        BookingRequestDto bookingRequestDto = new BookingRequestDto(null, 1);

        MissingScheduledFlightIdException exception = assertThrows(MissingScheduledFlightIdException.class, () -> flightBookingService.createBooking(bookingRequestDto, provideUserDetails("test")));
        assertEquals("You must provide the scheduled flight id", exception.getMessage());
    }

    @Test
    void bookingServiceCreateBookingNonExistingFlightId() {
        BookingRequestDto bookingRequestDto = new BookingRequestDto(1234567890L, 1);

        InvalidScheduledFlightIdException exception = assertThrows(InvalidScheduledFlightIdException.class, () -> flightBookingService.createBooking(bookingRequestDto, provideUserDetails("test")));
        assertEquals("The provided scheduled flight id is invalid", exception.getMessage());
    }

    @Test
    void bookingServiceCreateBookingMissingSeat() {
        BookingRequestDto bookingRequestDto = new BookingRequestDto(1L, null);

        InvalidSeatException exception = assertThrows(InvalidSeatException.class, () -> flightBookingService.createBooking(bookingRequestDto, provideUserDetails("test")));
        assertEquals("You must provide an amount of seat strictly greater than 0", exception.getMessage());
    }

    @Test
    void bookingServiceCreateBookingNegativeSeat() {
        BookingRequestDto bookingRequestDto = new BookingRequestDto(1L, -1);

        InvalidSeatException exception = assertThrows(InvalidSeatException.class, () -> flightBookingService.createBooking(bookingRequestDto, provideUserDetails("test")));
        assertEquals("You must provide an amount of seat strictly greater than 0", exception.getMessage());
    }

    @Test
    void bookingServiceCreateBookingExceedsCapacity() {
        BookingRequestDto bookingRequestDto = new BookingRequestDto(5L, 3);

        BookingExceedsCapacityException exception = assertThrows(BookingExceedsCapacityException.class, () -> flightBookingService.createBooking(bookingRequestDto, provideUserDetails("test")));
        assertEquals("Your booking exceeds the maximum remaining capacity of 2", exception.getMessage());
    }

    @Test
    void bookingServiceCreateBookingValid() {
        BookingRequestDto bookingRequestDto = new BookingRequestDto(5L, 1);

        BookingDto bookingDto = flightBookingService.createBooking(bookingRequestDto, provideUserDetails("test"));

        assertEquals(1, bookingDto.getSeats());
        assertNotNull(bookingDto.getScheduledFlight());
        assertNotNull(bookingDto.getId());
    }

    @Test
    void bookingServiceCreateBookingConcurrent() {
        // Multitasks could be started simultaneously to ensure the lock is effectively working. Leaving this out due to time constraint.
    }

    // Test should cover more scenarios to cover all methods and cases, leaving these out due to time constraint.
}
