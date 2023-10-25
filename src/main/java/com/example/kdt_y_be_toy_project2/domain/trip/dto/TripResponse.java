package com.example.kdt_y_be_toy_project2.domain.trip.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip}
 */
public record TripResponse(
        @NotNull Long tripId,
        @NotNull String tripName,
        @NotNull TimeSchedule timeSchedule,
        @NotNull TripDestinationEnum tripDestinationEnum,
        List<Itinerary> itineraries,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
    public static TripResponse fromEntity(Trip trip) {
        return new TripResponse(
                trip.getTripId(),
                trip.getTripName(),
                trip.getTimeSchedule(),
                trip.getTripDestinationEnum(),
                trip.getItineraries(),
                trip.getCreatedAt(),
                trip.getUpdatedAt()
        );
    }
}