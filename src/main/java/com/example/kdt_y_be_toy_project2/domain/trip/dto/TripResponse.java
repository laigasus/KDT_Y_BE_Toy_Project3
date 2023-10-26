package com.example.kdt_y_be_toy_project2.domain.trip.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip}
 */
public class TripResponse {

    public record CommonFields(
            @NotNull Long tripId,
            @NotNull String tripName,
            @NotNull TimeSchedule timeSchedule,
            @NotNull TripDestinationEnum tripDestinationEnum,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime updatedAt) {

        public static CommonFields fromEntity(Trip trip) {
            return new CommonFields(
                    trip.getTripId(),
                    trip.getTripName(),
                    trip.getTimeSchedule(),
                    trip.getTripDestinationEnum(),
                    trip.getCreatedAt(),
                    trip.getUpdatedAt()
            );
        }
    }

    public record AllTrips(
            CommonFields commonFields,
            List<String> itinerariesNames) {

        public static AllTrips fromEntity(Trip trip) {
            List<String> itinerariesNames = trip.getItineraries()
                    .stream()
                    .map(Itinerary::getItineraryName)
                    .collect(Collectors.toList());

            return new AllTrips(
                    CommonFields.fromEntity(trip),
                    itinerariesNames
            );
        }
    }

    public record TripById(
            CommonFields commonFields,
            List<Itinerary> itineraries) {

        public static TripById fromEntity(Trip trip) {
            return new TripById(
                    CommonFields.fromEntity(trip),
                    trip.getItineraries()
            );
        }
    }
}