package com.example.kdt_y_be_toy_project2.domain.trip.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class TripSchedule {
    @Comment("여행 스케줄 출발시각")
    private LocalDateTime departureDate;

    @Comment("여행 스케줄 도착시각")
    private LocalDateTime arrivalDate;

    @Builder
    private TripSchedule(LocalDateTime departureDate, LocalDateTime arrivalDate) {
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
}
