package com.example.kdt_y_be_toy_project2.domain.itinerary.dto;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Transport;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record ItineraryResponse(
        @Min(1L) Long id,
        @NotNull String itineraryName,
        Long trip_id,
        List<Accommodation> accommodation,
        List<Residence> residence,
        List<Transport> transport,
        @NotNull TimeSchedule timeSchedule,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt
) {
    public static ItineraryResponse fromEntity(Itinerary itinerary) {
        return new ItineraryResponse(
                itinerary.getItineraryId(),
                itinerary.getItineraryName(),
                itinerary.getTrip().getTripId(),
                itinerary.getAccommodation(),
                itinerary.getResidence(),
                itinerary.getTransport(),
                itinerary.getTimeSchedule(),
                itinerary.getCreatedAt(),
                itinerary.getUpdatedAt()
        );
    }
}
