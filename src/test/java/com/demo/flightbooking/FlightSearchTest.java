package com.demo.flightbooking;

import com.demo.flightbooking.dtos.ScheduledFlightDto;
import com.demo.flightbooking.exceptions.MissingArrivalException;
import com.demo.flightbooking.exceptions.MissingDateException;
import com.demo.flightbooking.exceptions.MissingDepartureException;
import com.demo.flightbooking.exceptions.PastDateException;
import com.demo.flightbooking.services.FlightSearchService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class FlightSearchTest {
    @Autowired
    private FlightSearchService flightSearchService;

    @BeforeAll
    static void setup(@Autowired DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("sql-test-data.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void searchServiceThrowsAnErrorMessageIfEmptyDeparture() {
        MissingDepartureException exception = assertThrows(MissingDepartureException.class, () -> flightSearchService.searchFlight("", "LFMN", LocalDate.now()));
        assertEquals("Departure is missing", exception.getMessage());
    }

    @Test
    void searchServiceThrowsAnErrorMessageIfMissingDeparture() {
        MissingDepartureException exception = assertThrows(MissingDepartureException.class, () -> flightSearchService.searchFlight(null, "LFMN", LocalDate.now()));
        assertEquals("Departure is missing", exception.getMessage());
    }

    @Test
    void searchServiceThrowsAnErrorMessageIfEmptyArrival() {
        MissingArrivalException exception = assertThrows(MissingArrivalException.class, () -> flightSearchService.searchFlight("EBBR", "", LocalDate.now()));
        assertEquals("Arrival is missing", exception.getMessage());
    }

    @Test
    void searchServiceThrowsAnErrorMessageIfMissingArrival() {
        MissingArrivalException exception = assertThrows(MissingArrivalException.class, () -> flightSearchService.searchFlight("EBBR", null, LocalDate.now()));
        assertEquals("Arrival is missing", exception.getMessage());
    }

    @Test
    void searchServiceThrowsAnErrorMessageIfMissingDate() {
        MissingDateException exception = assertThrows(MissingDateException.class, () -> flightSearchService.searchFlight("EBBR", "LFMN", null));
        assertEquals("Date is missing", exception.getMessage());
    }

    @Test
    void searchServiceThrowsAnErrorMessageIfPastDate() {
        PastDateException exception = assertThrows(PastDateException.class, () -> flightSearchService.searchFlight("EBBR", "LFMN", LocalDate.now().minusDays(1)));
        assertEquals("Date must be today or in the future", exception.getMessage());
    }

    @Test
    void searchServiceDoesNotShowFlightFull() {
        ScheduledFlightDto scheduledFlightDto = flightSearchService.searchFlight("EBBR", "LFMN", LocalDate.now().plusDays(7));
        assertNull(scheduledFlightDto);
    }

    @Test
    void searchServiceListingAllOnlyShowsFutureFlights() {
        List<ScheduledFlightDto> scheduledFlights = flightSearchService.listFutureAvailableFlights();
        scheduledFlights.forEach(sf -> assertFalse(LocalDate.now().isAfter(sf.getDepartureDate())));
    }

    @Test
    void searchServiceListingAllOnlyShowsAvailableFlights() {
        List<ScheduledFlightDto> scheduledFlights = flightSearchService.listFutureAvailableFlights();
        scheduledFlights.forEach(sf -> assertFalse(sf.getDeparture().equals("EBBR") && sf.getArrival().equals("LFMN") && sf.getDepartureDate().equals(LocalDate.now().plusDays(7))));
    }
}
