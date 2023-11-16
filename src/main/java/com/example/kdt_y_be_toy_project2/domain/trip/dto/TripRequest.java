package com.example.kdt_y_be_toy_project2.domain.trip.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record TripRequest(
        @NotNull
        @Size(min = 1, max = 50, message = "tripName size must 1-50")
        String tripName,
        @NotNull
        TimeSchedule timeSchedule,
        @NotNull
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