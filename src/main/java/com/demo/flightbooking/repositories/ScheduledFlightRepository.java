package com.demo.flightbooking.repositories;

import com.demo.flightbooking.entities.ScheduledFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduledFlightRepository  extends JpaRepository<ScheduledFlight, Long> {
    Optional<ScheduledFlight> findByFlightDepartureAndFlightArrivalAndDepartureDate(String departure, String arrival, LocalDate date);

    @Query("SELECT sf " +
            "FROM ScheduledFlight sf " +
            "WHERE sf.departureDate >= CURRENT_DATE " +
            "AND sf.flight.maximumCapacity > sf.passengerCount")
    List<ScheduledFlight> findFutureFlightsAvailable();
}
