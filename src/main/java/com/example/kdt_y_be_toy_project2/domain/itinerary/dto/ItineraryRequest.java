package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Transportation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryRequest {

    private Residence residence;
    private Accommodation accommodation;
    private Transportation transportation;
}
