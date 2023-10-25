package com.example.kdt_y_be_toy_project2.domain.trip.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripSchedule;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripResponse {
    private Long id;
    private String tripName;
    private TripSchedule tripSchedule;
    private TripDestinationEnum tripDestinationEnum;
    private List<Itinerary> itineraryList;

    public static TripResponse fromEntity(Trip trip){
        return TripResponse.builder()
                .id(trip.getId())
                .tripName(trip.getTripName())
                .tripSchedule(trip.getTripSchedule())
                .tripDestinationEnum(trip.getTripDestinationEnum())
                .itineraryList(trip.getItineraries())
                .build();
    }
}