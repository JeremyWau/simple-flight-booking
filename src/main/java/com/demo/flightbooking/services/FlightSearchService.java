package com.demo.flightbooking.services;

import com.demo.flightbooking.dtos.ScheduledFlightDto;
import com.demo.flightbooking.entities.ScheduledFlight;
import com.demo.flightbooking.exceptions.MissingArrivalException;
import com.demo.flightbooking.exceptions.MissingDateException;
import com.demo.flightbooking.exceptions.MissingDepartureException;
import com.demo.flightbooking.exceptions.PastDateException;
import com.demo.flightbooking.mappers.ScheduledFlightMapper;
import com.demo.flightbooking.repositories.ScheduledFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightSearchService {

    @Autowired
    private ScheduledFlightRepository scheduledFlightRepository;

    @Autowired
    private ScheduledFlightMapper scheduledFlightMapper;

    public ScheduledFlightDto searchFlight(String departure, String arrival, LocalDate date) {
        if(departure == null || departure.isEmpty())
            throw new MissingDepartureException();
        if(arrival == null || arrival.isEmpty())
            throw new MissingArrivalException();
        if(date == null)
            throw new MissingDateException();
        if(date.isBefore(LocalDate.now()))
            throw new PastDateException();

        ScheduledFlight scheduledFlight = scheduledFlightRepository.findByFlightDepartureAndFlightArrivalAndDepartureDate(departure, arrival, date).orElse(null);

        return scheduledFlightMapper.mapToDto(scheduledFlight != null && scheduledFlight.getPassengerCount() < scheduledFlight.getFlight().getMaximumCapacity() ? scheduledFlight : null);
    }

    public List<ScheduledFlightDto> listFutureAvailableFlights() {
        return scheduledFlightRepository.findFutureFlightsAvailable().stream().map(scheduledFlightMapper::mapToDto).toList();
    }
}
