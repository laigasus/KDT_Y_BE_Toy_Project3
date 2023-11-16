package com.example.kdt_y_be_toy_project2.domain.itinerary.repository;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    List<Itinerary> findByTripTripId(Long tripId);
}