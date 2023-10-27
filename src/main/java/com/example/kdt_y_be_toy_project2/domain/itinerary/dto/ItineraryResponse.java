package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.AccommodationDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ResidenceDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.TransportDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.global.dto.TimeScheduleDTO;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ItineraryResponse(
        @Min(1L)
        @Schema(name = "여정 아이디", example = "7")
        Long id,

        @NotNull
        @Schema(name = "여정 이름", example = "옥크나이트와 떠나는 코딩숙박")
        String itineraryName,

        Long trip_id,

        List<AccommodationDTO> accommodation,

        @Schema(name = "체류지", example = "속초")
        List<ResidenceDTO> residence,
        @Schema(name = "교통수단", example = "버스")

        List<TransportDTO> transport,
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
                TransportDTO.fromEntities(itinerary.getTransport()),
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
