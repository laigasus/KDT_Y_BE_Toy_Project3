package com.example.kdt_y_be_toy_project2.domain.trip.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TripResponse {


    public record TripInfo(

            @NotNull
            @Schema(name = "여행 id", example = "4")
            Long tripId,


            @NotNull
            @Schema(name = "여행 이름", example = "강원도 여행")
            String tripName,

            @NotNull
            @Schema(name = "여행 날짜", example = "2023-11-11")
            TimeSchedule timeSchedule,

            @NotNull
            @Schema(name = "여행 목적지", example = "국내")
            TripDestinationEnum tripDestinationEnum,

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