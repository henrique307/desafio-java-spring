package com.example.desafio_java_spring.domain.ride;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RideRepository extends JpaRepository<Ride, UUID> {
    Page<Ride> findByCostumerId(UUID costumerId, Pageable pageable);
    Page<Ride> findByDriverId(UUID driverId, Pageable pageable);
    Page<Ride> findByStatus(RideStatus rideStatus, Pageable pageable);
}
