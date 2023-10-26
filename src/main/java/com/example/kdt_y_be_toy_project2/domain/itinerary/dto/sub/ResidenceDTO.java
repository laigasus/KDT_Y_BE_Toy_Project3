package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;

import java.time.LocalDateTime;
import java.util.List;

public record ResidenceDTO(
        String residenceName,
        LocalDateTime stayTime,
        LocalDateTime leaveTime
) {
    private static ResidenceDTO fromEntity(Residence residence) {
        return new ResidenceDTO(
                residence.getResidenceName(),
                residence.getResidenceTimeSchedule().getStartTime(),
                residence.getResidenceTimeSchedule().getEndTime()
        );
    }

    public static List<ResidenceDTO> fromEntities(List<Residence> residences) {
        return residences.stream().map(ResidenceDTO::fromEntity).toList();
    }
}