package com.example.kdt_y_be_toy_project2.global.dto;

import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record TimeScheduleDTO(
        String startTime,
        String endTime
) {
    public TimeScheduleDTO(
            LocalDateTime startTime,
            LocalDateTime endTime,
            boolean isDateFormat
    ) {
        this(
                isDateFormat ? TimeUtils.formatDate(startTime) : TimeUtils.formatDateTime(startTime),
                isDateFormat ? TimeUtils.formatDate(endTime) : TimeUtils.formatDateTime(endTime)
        );
    }
}
