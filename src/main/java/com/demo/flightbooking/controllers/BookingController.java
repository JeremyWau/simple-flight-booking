package com.demo.flightbooking.controllers;

import com.demo.flightbooking.dtos.BookingDto;
import com.demo.flightbooking.dtos.BookingRequestDto;
import com.demo.flightbooking.services.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/protected/flight-booking")
public class BookingController {
    @Autowired
    private FlightBookingService flightBookingService;

    @PostMapping("book")
    public ResponseEntity<BookingDto> createBooking(@AuthenticationPrincipal UserDetails userDetails, @RequestBody BookingRequestDto bookingRequestDto){
        return ResponseEntity.ok(flightBookingService.createBooking(bookingRequestDto, userDetails));
    }

    @GetMapping("list")
    public ResponseEntity<List<BookingDto>> listBookings(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(flightBookingService.findAllBookings(userDetails));
    }

    @GetMapping("{id}/view")
    public ResponseEntity<BookingDto> viewBooking(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id){
        return ResponseEntity.ok(flightBookingService.getBooking(id, userDetails));
    }

    @DeleteMapping("{id}/cancel")
    public ResponseEntity<String> cancelBooking(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id){
        flightBookingService.deleteBooking(id, userDetails);
        return ResponseEntity.ok("Booking deleted");
    }
}
