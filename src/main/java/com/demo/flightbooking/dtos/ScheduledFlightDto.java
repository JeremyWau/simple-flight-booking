package com.demo.flightbooking.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledFlightDto {
    private Long id;
    private String departure;
    private String arrival;
    private LocalDate departureDate;
    private Long passengerCount;
}
