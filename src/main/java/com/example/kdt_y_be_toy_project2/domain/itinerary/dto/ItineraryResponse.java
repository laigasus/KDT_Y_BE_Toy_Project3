package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.AccommodationDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ActivityDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ResidenceDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.global.dto.TimeScheduleDTO;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record ItineraryResponse(
        @Min(1L)
        Long id,
        @NotNull
        String itineraryName,
        Long tripId,
        List<AccommodationDTO> accommodation,
        List<ResidenceDTO> residence,
        List<ActivityDTO> activity,
        @NotNull TimeScheduleDTO timeSchedule,
        @NotNull String createdAt,
        @NotNull String updatedAt
) {
    public static ItineraryResponse fromEntity(Itinerary itinerary) {
        return new ItineraryResponse(
                itinerary.getItineraryId(),
                itinerary.getItineraryName(),
                itinerary.getTrip().getTripId(),
                AccommodationDTO.fromEntities(itinerary.getAccommodation()),
                ResidenceDTO.fromEntities(itinerary.getResidence()),
                ActivityDTO.fromEntities(itinerary.getActivity()),
                new TimeScheduleDTO(
                        itinerary.getTimeSchedule().getStartTime(),
                        itinerary.getTimeSchedule().getEndTime(),
                        true
                ),
                Optional.ofNullable(itinerary.getCreatedAt())
                        .map(TimeUtils::formatDateTime)
                        .orElse(TimeUtils.formatDateTime(LocalDateTime.now())),
                Optional.ofNullable(itinerary.getUpdatedAt())
                        .map(TimeUtils::formatDateTime)
                        .orElse(TimeUtils.formatDateTime(LocalDateTime.now()))
        );
    }
}
