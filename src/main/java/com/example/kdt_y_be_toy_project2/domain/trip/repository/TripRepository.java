package com.example.kdt_y_be_toy_project2.domain.trip.repository;


import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
