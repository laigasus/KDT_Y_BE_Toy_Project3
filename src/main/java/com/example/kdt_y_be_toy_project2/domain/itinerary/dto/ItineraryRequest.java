package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Transportation;
import jakarta.validation.constraints.NotNull;

public record ItineraryRequest(
        @NotNull String name,
        @NotNull Residence residence,
        @NotNull Accommodation accommodation,
        @NotNull Transportation transportation
) {
    public Itinerary toEntity() {
        return Itinerary.builder()
                .name(this.name)
                .residence(this.residence)
                .accommodation(this.accommodation)
                .transportation(this.transportation)
                .build();
    }
}
