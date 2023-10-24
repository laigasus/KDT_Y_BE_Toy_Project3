package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Transportation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItineraryResponse(
        @Min(1L) Long id,
        @NotNull Residence residence,
        @NotNull Accommodation accommodation,
        @NotNull Transportation transportation
) {
    public static ItineraryResponse fromEntity(Itinerary itinerary) {
        return new ItineraryResponse(
                itinerary.getId(),
                itinerary.getResidence(),
                itinerary.getAccommodation(),
                itinerary.getTransportation()
        );
    }
}
