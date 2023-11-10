package com.example.kdt_y_be_toy_project2.domain.trip.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.global.dto.TimeScheduleDTO;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class TripResponse {

    public record TripInfo(
            @Positive
            @Schema(example = "4")
            Long tripId,
            @NotNull
            @Schema(example = "강원도 여행")
            String tripName,
            @NotNull
            TimeScheduleDTO timeSchedule,
            @NotNull
            String tripDestinationEnum,
            @NotNull String createdAt,
            @NotNull String updatedAt) {

        public static TripInfo fromEntity(Trip trip) {
            return new TripInfo(
                    trip.getTripId(),
                    trip.getTripName(),
                    new TimeScheduleDTO(
                            trip.getTimeSchedule().getStartTime(),
                            trip.getTimeSchedule().getEndTime(),
                            true
                    ),
                    trip.getTripDestinationEnum().getMethod(),
                    TimeUtils.formatDateTime(trip.getCreatedAt()),
                    TimeUtils.formatDateTime(trip.getUpdatedAt())
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
                    .toList();

            return new AllTrips(
                    TripInfo.fromEntity(trip),
                    itinerariesNames
            );
        }
    }

    public record TripById(
            TripInfo tripInfo,
            List<ItineraryResponse> itineraries) {

        public static TripById fromEntity(Trip trip) {
            return new TripById(
                    TripInfo.fromEntity(trip),
                    TripById.convertItineriesToDTOList(trip.getItineraries())
            );
        }

        private static List<ItineraryResponse> convertItineriesToDTOList(List<Itinerary> itineraries) {
            return itineraries.stream().map(ItineraryResponse::fromEntity).toList();
        }
    }
}