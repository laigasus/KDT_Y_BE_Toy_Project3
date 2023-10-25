package com.example.kdt_y_be_toy_project2.domain.trip.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripSchedule;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripRequest {
    @NotNull
    @Size(min =1,max = 50,message = "tripName size must 1-50")
    private String tripName;

    @NotNull
    private TripSchedule tripSchedule;

    @NotNull
    private TripDestinationEnum tripDestinationEnum;

    private List<Itinerary> itineraries;
}