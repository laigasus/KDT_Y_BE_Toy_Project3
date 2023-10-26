package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record AccommodationDTO(
        String accommodationName,
        LocalDateTime checkIn,
        LocalDateTime checkOut
) {
    private static AccommodationDTO fromEntity(Accommodation accommodation) {
        return new AccommodationDTO(
                accommodation.getAccommodationName(),
                accommodation.getAccommodationTimeSchedule().getStartTime(),
                accommodation.getAccommodationTimeSchedule().getEndTime()
        );
    }

    public static List<AccommodationDTO> fromEntities(List<Accommodation> accommodations) {
        return accommodations.stream().map(AccommodationDTO::fromEntity).collect(Collectors.toList());
    }
}






