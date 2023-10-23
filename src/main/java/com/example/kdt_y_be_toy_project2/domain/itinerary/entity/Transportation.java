package com.example.kdt_y_be_toy_project2.domain.itinerary.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Transportation {
    @Enumerated(EnumType.STRING)
    @Comment("이동수단")
    private TransportationEnum transportationEnum;

    @Comment("이동수단 출발지")
    private String transportationStartPoint;
    @Comment("이동수단 도착지")
    private String transportationEndPoint;

    @Comment("이동수단 출발시각")
    private LocalDateTime transportationDepartureTime;

    @Comment("이동수단 도착시각")
    private LocalDateTime transportationArrivalTime;
    // TODO 교통수단에서 출발시간, 도착시간 이 숙소에서의 시각과 다를 수 있음

    @Builder
    private Transportation(TransportationEnum transportationEnum, String transportationStartPoint, String transportationEndPoint, LocalDateTime transportationDepartureTime, LocalDateTime transportationArrivalTime) {
        this.transportationEnum = transportationEnum;
        this.transportationStartPoint = transportationStartPoint;
        this.transportationEndPoint = transportationEndPoint;
        this.transportationDepartureTime = transportationDepartureTime;
        this.transportationArrivalTime = transportationArrivalTime;
    }
}
