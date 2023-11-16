package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;

import java.util.List;

public record ResidenceDTO(
        String residenceName,
        String stayTime,
        String leaveTime

) {
    private static ResidenceDTO fromEntity(Residence residence) {
        return new ResidenceDTO(
                residence.getResidenceName(),
                TimeUtils.formatDateTime(residence.getResidenceTimeSchedule().getStartTime()),
                TimeUtils.formatDateTime(residence.getResidenceTimeSchedule().getEndTime())
        );
    }

    public static List<ResidenceDTO> fromEntities(List<Residence> residences) {
        return residences.stream().map(ResidenceDTO::fromEntity).toList();
    }
}