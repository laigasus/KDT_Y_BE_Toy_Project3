package com.example.kdt_y_be_toy_project2.domain.trip.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.TripCommentGetResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.global.dto.TimeScheduleDTO;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TripResponse {

    public record TripInfo(
            @Positive
            Long tripId,
            @NotNull
            String tripName,
            @NotNull
            TimeScheduleDTO timeSchedule,
            @NotNull
            String tripDestinationEnum,
            @NotNull String createdAt,
            @NotNull String updatedAt,
            @NotNull Integer likes
    ) {

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
                    Optional.ofNullable(trip.getCreatedAt())
                            .map(TimeUtils::formatDateTime)
                            .orElse(TimeUtils.formatDateTime(LocalDateTime.now())),
                    Optional.ofNullable(trip.getUpdatedAt())
                            .map(TimeUtils::formatDateTime)
                            .orElse(TimeUtils.formatDateTime(LocalDateTime.now())),
                    trip.getUserLikes().size()
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
            List<ItineraryResponse> itineraries,
            List<TripCommentGetResponse> tripComments) {

        public static TripById fromEntity(Trip trip, List<TripCommentGetResponse> tripCommentGetResponses) {
            return new TripById(
                    TripInfo.fromEntity(trip),
                    TripById.convertItineriesToDTOList(trip.getItineraries()),
                    tripCommentGetResponses
            );
        }

        private static List<ItineraryResponse> convertItineriesToDTOList(List<Itinerary> itineraries) {
            return itineraries.stream().map(ItineraryResponse::fromEntity).toList();
        }
    }

    public record TripByKeyWord(
            @Positive
            Long tripId,
            @NotNull
            String tripName,
            @NotNull String updatedAt,
            @NotNull Integer likes) {

        public static TripByKeyWord fromEntity(Trip trip) {
            return new TripByKeyWord(
                    trip.getTripId(),
                    trip.getTripName(),
                    Optional.ofNullable(trip.getUpdatedAt())
                            .map(TimeUtils::formatDateTime)
                            .orElse(TimeUtils.formatDateTime(LocalDateTime.now())),
                    trip.getUserLikes().size()
            );
        }
    }
}