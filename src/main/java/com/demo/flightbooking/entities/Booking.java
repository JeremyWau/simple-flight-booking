package com.demo.flightbooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings", indexes = {
        @Index(name = "idx_booking_scheduled_flight ", columnList = "scheduled_flight_id")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer seats;

    @ManyToOne()
    @JoinColumn(name = "scheduled_flight_id", referencedColumnName = "id", nullable = false)
    private ScheduledFlight scheduledFlight;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserSimplified user;
}
