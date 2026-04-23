package com.demo.flightbooking.mappers;

import com.demo.flightbooking.dtos.BookingDto;
import com.demo.flightbooking.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper implements Mapper<BookingDto, Booking> {
    @Autowired
    private ScheduledFlightMapper scheduledFlightMapper;

    @Override
    public BookingDto mapToDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setSeats(booking.getSeats());
        bookingDto.setScheduledFlight(scheduledFlightMapper.mapToDto(booking.getScheduledFlight()));
        return bookingDto;
    }

    @Override
    public Booking mapToEntity(BookingDto bookingDto) {
        //Unused
        return null;
    }
}
