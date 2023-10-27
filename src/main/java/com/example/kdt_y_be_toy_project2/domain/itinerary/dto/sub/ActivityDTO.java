package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Activity;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ActivityDTO(
        @Schema(example = "버스")
        String transportEnum,

        @Schema(name = "출발지", example = "서울")
        String departurePlace,

        @Schema(name = "도착지", example = "속초")
        String arrivalPlace,

        @Schema(name = "설명", example = "속초")
        String description,

        @Schema(name = "출발 시각")
        String activityStart,

        @Schema(name = "도착 시각")
        String activityEnd

) {
    private static ActivityDTO fromEntity(Activity activity) {
        return new ActivityDTO(
                activity.getTransportEnum().getMethod(),
                activity.getDeparturePlace(),
                activity.getArrivalPlace(),
                activity.getDescription(),
                TimeUtils.formatDateTime(activity.getActivityTimeSchedule().getStartTime()),
                TimeUtils.formatDateTime(activity.getActivityTimeSchedule().getEndTime())
        );
    }

    public static List<ActivityDTO> fromEntities(List<Activity> activities) {
        return activities.stream().map(ActivityDTO::fromEntity).toList();
    }
}