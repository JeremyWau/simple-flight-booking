package com.demo.flightbooking.repositories;

import com.demo.flightbooking.entities.UserSimplified;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSimplifiedRepository extends JpaRepository<UserSimplified, Long> {
    Optional<UserSimplified> findByUsername(String username);
}