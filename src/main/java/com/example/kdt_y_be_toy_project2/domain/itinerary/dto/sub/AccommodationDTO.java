package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Accommodation;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record AccommodationDTO(
        @Schema(name = "호텔 이름", example = "롯데 호텔")
        String accommodationName,

        @Schema(name = "체크인 시간",example = "2023-11-11-16:00")
        String checkIn,
        @Schema(name = "체크 아웃 시간",example = "2023-11-12-13:00")
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






