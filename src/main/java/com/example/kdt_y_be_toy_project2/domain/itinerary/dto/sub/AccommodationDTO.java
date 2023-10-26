package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;

import java.util.List;

public record AccommodationDTO(
        String accommodationName,
        String checkIn,
        String checkOut
) {
    private static AccommodationDTO fromEntity(Accommodation accommodation) {
        return new AccommodationDTO(
                accommodation.getAccommodationName(),
                TimeUtils.formatDateTime(accommodation.getAccommodationTimeSchedule().getStartTime()),
                TimeUtils.formatDateTime(accommodation.getAccommodationTimeSchedule().getEndTime())
        );
    }

    public static List<AccommodationDTO> fromEntities(List<Accommodation> accommodations) {
        return accommodations.stream().map(AccommodationDTO::fromEntity).toList();
    }
}






