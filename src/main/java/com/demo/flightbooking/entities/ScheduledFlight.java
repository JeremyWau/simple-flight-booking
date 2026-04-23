package com.demo.flightbooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;

@Entity
@Table(name = "scheduled_flights",
        //indexes = { @Index(name = "idx_scheduled_date_flight", columnList = "departureDate, flight_id")},
        uniqueConstraints = {@UniqueConstraint(name = "u_flight_date", columnNames = {"flight_id", "departure_date"})}
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "flight_id", referencedColumnName = "id", nullable = false)
    private Flight flight;

    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;

    @Formula("(SELECT COALESCE(SUM(b.seats), 0) FROM bookings b WHERE b.scheduled_flight_id = id)")
    private Integer passengerCount;
}
