package com.demo.flightbooking.services;

import com.demo.flightbooking.dtos.BookingDto;
import com.demo.flightbooking.dtos.BookingRequestDto;
import com.demo.flightbooking.entities.Booking;
import com.demo.flightbooking.entities.ScheduledFlight;
import com.demo.flightbooking.exceptions.*;
import com.demo.flightbooking.mappers.BookingMapper;
import com.demo.flightbooking.repositories.BookingRepository;
import com.demo.flightbooking.repositories.ScheduledFlightRepository;
import com.demo.flightbooking.repositories.UserSimplifiedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FlightBookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private ScheduledFlightRepository scheduledFlightRepository;
    @Autowired
    private UserSimplifiedRepository userSimplifiedRepository;

    @Transactional
    public BookingDto createBooking(BookingRequestDto bookingRequestDto, UserDetails userDetails) {
        if(bookingRequestDto.getScheduledFlightId() == null)
            throw new MissingScheduledFlightIdException();
        if(bookingRequestDto.getSeats() == null || bookingRequestDto.getSeats() <= 0)
            throw new InvalidSeatException();

        Optional<ScheduledFlight> scheduledFlightOptional = scheduledFlightRepository.findWithLockingById(bookingRequestDto.getScheduledFlightId());
        if(scheduledFlightOptional.isEmpty())
            throw new InvalidScheduledFlightIdException();

        ScheduledFlight scheduledFlight = scheduledFlightOptional.get();
        if(bookingRequestDto.getSeats()+scheduledFlight.getPassengerCount() > scheduledFlight.getFlight().getMaximumCapacity())
            throw new BookingExceedsCapacityException(scheduledFlight.getFlight().getMaximumCapacity() - scheduledFlight.getPassengerCount());

        Booking booking = new Booking();
        booking.setUser(userSimplifiedRepository.findByUsername(userDetails.getUsername()).orElseThrow());
        booking.setScheduledFlight(scheduledFlight);
        booking.setSeats(bookingRequestDto.getSeats());

        return bookingMapper.mapToDto(bookingRepository.save(booking));
    }

    public List<BookingDto> findAllBookings(UserDetails userDetails) {
        return bookingRepository.findAllByUserId(userSimplifiedRepository.findByUsername(userDetails.getUsername()).orElseThrow().getId()).stream().map(bookingMapper::mapToDto).toList();
    }

    public BookingDto getBooking(Long id, UserDetails userDetails) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if(bookingOptional.isEmpty())
            throw new InvalidBookingIdOrOwnershipException();

        Booking booking = bookingOptional.get();
        if(!booking.getUser().getId().equals(userSimplifiedRepository.findByUsername(userDetails.getUsername()).orElseThrow().getId()))
            throw new InvalidBookingIdOrOwnershipException();

        return bookingMapper.mapToDto(booking);
    }

    public void deleteBooking(Long id, UserDetails userDetails) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if(bookingOptional.isEmpty())
            throw new InvalidBookingIdOrOwnershipException();

        Booking booking = bookingOptional.get();
        if(!booking.getUser().getId().equals(userSimplifiedRepository.findByUsername(userDetails.getUsername()).orElseThrow().getId()))
            throw new InvalidBookingIdOrOwnershipException();

        bookingRepository.delete(booking);
    }
}
