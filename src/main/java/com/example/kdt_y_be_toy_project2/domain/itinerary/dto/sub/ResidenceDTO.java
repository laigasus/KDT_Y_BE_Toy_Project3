package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Residence;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ResidenceDTO(
        @Schema(name = "체류지 이름", example = "속초")
        String residenceName,
        @Schema(name = "거주 기간")
        String stayTime,
        @Schema(name = "떠나는 기간")
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