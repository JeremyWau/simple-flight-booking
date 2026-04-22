package com.demo.flightbooking.mappers;

import com.demo.flightbooking.dtos.ScheduledFlightDto;
import com.demo.flightbooking.entities.ScheduledFlight;
import org.springframework.stereotype.Service;

@Service
public class ScheduledFlightMapper implements Mapper<ScheduledFlightDto, ScheduledFlight> {

    @Override
    public ScheduledFlightDto mapToDto(ScheduledFlight scheduledFlight) {
        if(scheduledFlight == null)
            return null;

        ScheduledFlightDto scheduledFlightDto = new ScheduledFlightDto();
        scheduledFlightDto.setId(scheduledFlight.getId());
        scheduledFlightDto.setDeparture(scheduledFlight.getFlight().getDeparture());
        scheduledFlightDto.setArrival(scheduledFlight.getFlight().getArrival());
        scheduledFlightDto.setDepartureDate(scheduledFlight.getDepartureDate());
        scheduledFlightDto.setPassengerCount(scheduledFlight.getPassengerCount());
        return scheduledFlightDto;
    }

    @Override
    public ScheduledFlight mapToEntity(ScheduledFlightDto scheduledFlightDto) {
        // Not needed.
        return null;
    }
}
