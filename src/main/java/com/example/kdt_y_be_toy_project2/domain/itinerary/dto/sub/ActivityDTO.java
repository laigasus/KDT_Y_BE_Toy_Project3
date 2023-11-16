package com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Activity;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;

import java.util.List;

public record ActivityDTO(
        String transportEnum,

        String departurePlace,

        String arrivalPlace,

        String description,

        String activityStart,

        String activityEnd,

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
