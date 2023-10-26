package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Transport;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * DTO for {@link com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary}
 */
public record ItineraryRequest(
        @Schema(name = "여정 이름", example = "옥크나이트와 떠나는 코딩숙박")
        String itineraryName,

        Trip trip,
        @Schema(name = "숙소", example = "호텔")
        List<Accommodation> accommodation,

        @Schema(name = "체류지", example = "속초")
        List<Residence> residence,
        @Schema(name = "교통수단", example = "버스")
        List<Transport> transport,
        TimeSchedule timeSchedule
) {
    public Itinerary toEntity() {
        return Itinerary.builder()
                .itineraryName(itineraryName)
                .cities(residence)
                .accommodations(accommodation)
                .transports(transport)
                .timeSchedule(timeSchedule)
                .build();
    }
}