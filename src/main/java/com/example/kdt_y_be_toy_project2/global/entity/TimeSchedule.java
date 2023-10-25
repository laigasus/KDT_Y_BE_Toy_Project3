package com.example.kdt_y_be_toy_project2.global.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class TimeSchedule {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
