package com.demo.flightbooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "flights",
        //indexes = { @Index(name = "idx_flight_route", columnList = "departure, arrival")},
        uniqueConstraints = {@UniqueConstraint(name = "u_departure_arrival", columnNames = {"departure", "arrival"})}
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String arrival;

    @Column(name = "maximum_capacity", nullable = false)
    private Integer maximumCapacity;
}
