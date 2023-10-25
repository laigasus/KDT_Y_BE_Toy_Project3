package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Transport;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;

import java.util.List;

/**
 * DTO for {@link com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary}
 */
public record ItineraryRequest(
        String itineraryName,
        Trip trip,
        List<Accommodation> accommodation,
        List<Residence> residence,
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