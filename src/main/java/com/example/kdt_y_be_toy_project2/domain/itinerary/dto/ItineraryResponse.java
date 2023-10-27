package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.AccommodationDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ActivityDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ResidenceDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.global.dto.TimeScheduleDTO;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ItineraryResponse(
        @Min(1L)
        @Schema(example = "7")
        Long id,

        @NotNull
        @Schema(example = "옥크나이트와 떠나는 코딩숙박")
        String itineraryName,

        Long trip_id,

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
                TimeUtils.formatDateTime(itinerary.getCreatedAt()),
                TimeUtils.formatDateTime(itinerary.getUpdatedAt())
        );
    }
}
