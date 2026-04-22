package com.demo.flightbooking.controllers;

import com.demo.flightbooking.dtos.ScheduledFlightDto;
import com.demo.flightbooking.services.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/public/flight-search")
public class FlightSearchController {
    @Autowired
    private FlightSearchService flightSearchService;

    @GetMapping("search")
    public ResponseEntity<ScheduledFlightDto> search(@RequestParam(name = "departure") String departure, @RequestParam(name = "arrival") String arrival, @RequestParam(name = "date") LocalDate date) {
        ScheduledFlightDto scheduledFlightDto = flightSearchService.searchFlight(departure, arrival, date);
        return scheduledFlightDto != null ? ResponseEntity.ok(scheduledFlightDto) : ResponseEntity.notFound().build();
    }

    @GetMapping("future-available")
    public ResponseEntity<List<ScheduledFlightDto>> listFutureAvailableFlights(){
        return ResponseEntity.ok(flightSearchService.listFutureAvailableFlights());
    }
}
