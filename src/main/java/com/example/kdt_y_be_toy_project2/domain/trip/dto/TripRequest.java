package com.example.kdt_y_be_toy_project2.domain.trip.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * DTO for {@link com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip}
 */
public record TripRequest(
        @NotNull
        @Size(min = 1, max = 50, message = "tripName size must 1-50")
        @Schema(name = "여행 이름", example = "강원도 여행")
        String tripName,
        @NotNull
        @Schema(name = "여행 날짜", example = "2023-11-11")
        TimeSchedule timeSchedule,
        @NotNull
        @Schema(name = "여행 목적지", example = "국내")
        TripDestinationEnum tripDestinationEnum,
        List<Itinerary> itineraries
) {
    public Trip toEntity() {
        return Trip.builder()
                .tripName(tripName)
                .timeSchedule(timeSchedule)
                .tripDestinationEnum(tripDestinationEnum)
                .build();
    }
}