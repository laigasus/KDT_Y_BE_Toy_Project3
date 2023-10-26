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


    public record TripInfo(
            @NotNull Long tripId,
            @NotNull String tripName,
            @NotNull TimeSchedule timeSchedule,
            @NotNull TripDestinationEnum tripDestinationEnum,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime updatedAt) {


        public static TripInfo fromEntity(Trip trip) {
            return new TripInfo(
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
            TripInfo tripInfo,

            List<String> itinerariesNames) {

        public static AllTrips fromEntity(Trip trip) {
            List<String> itinerariesNames = trip.getItineraries()
                    .stream()
                    .map(Itinerary::getItineraryName)
                    .collect(Collectors.toList());

            return new AllTrips(
                    TripInfo.fromEntity(trip),
                    itinerariesNames
            );
        }
    }

    public record TripById(
            TripInfo tripInfo,
            List<Itinerary> itineraries) {

        public static TripById fromEntity(Trip trip) {
            return new TripById(
                    TripInfo.fromEntity(trip),
                    trip.getItineraries()
            );
        }
    }
}