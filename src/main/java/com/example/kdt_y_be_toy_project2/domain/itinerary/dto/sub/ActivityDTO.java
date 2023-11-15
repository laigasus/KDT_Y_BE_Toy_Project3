package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Activity;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ActivityDTO(
        @Schema(example = "KTX")
        String transportEnum,

        @Schema(example = "집")
        String departurePlace,

        @Schema(example = "부산역")
        String arrivalPlace,

        @Schema(example = "Jungang-daero, 초량제3동 Dong-gu, Busan, South Korea")
        String description,

        @Schema(example = "2023-01-02T14:00:00")
        String activityStart,

        @Schema(example = "2023-01-03T11:00:00")
        String activityEnd,

        @Schema(example = "부산 도착 후 주소")
        String arrivalAddress
) {
    private static ActivityDTO fromEntity(Activity activity) {
        return new ActivityDTO(
                activity.getTransportEnum().getMethod(),
                activity.getDeparturePlace(),
                activity.getArrivalPlace(),
                activity.getDescription(),
                TimeUtils.formatDateTime(activity.getActivityTimeSchedule().getStartTime()),
                TimeUtils.formatDateTime(activity.getActivityTimeSchedule().getEndTime()),
                activity.getArrivalAddress()

        );
    }

    public static List<ActivityDTO> fromEntities(List<Activity> activities) {
        return activities.stream().map(ActivityDTO::fromEntity).toList();
    }
}
