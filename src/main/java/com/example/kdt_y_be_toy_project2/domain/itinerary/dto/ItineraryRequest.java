package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Activity;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * DTO for {@link Itinerary}
 */
public record ItineraryRequest(
        @Schema(example = "옥크나이트와 떠나는 코딩숙박")
        String itineraryName,

        @Schema(hidden = true)
        Trip trip,
        List<Accommodation> accommodation,

        List<Residence> residence,
        List<Activity> activity,
        TimeSchedule timeSchedule
) {
    public Itinerary toEntity() {
        return Itinerary.builder()
                .itineraryName(itineraryName)
                .residences(residence)
                .accommodations(accommodation)
                .activities(activity)
                .timeSchedule(timeSchedule)
                .build();
    }
}