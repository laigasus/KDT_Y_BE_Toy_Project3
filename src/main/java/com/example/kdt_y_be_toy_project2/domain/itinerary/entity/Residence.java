package com.example.kdt_y_be_toy_project2.domain.itinerary.entity;

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
public class Residence {

    @Comment("체류지 장소명")
    private String residenceName;

    @Comment("체류지 출발일시")
    private LocalDateTime residenceDepartureTime;


    @Comment("체류지 도착일시")
    private LocalDateTime residenceArrivalTime;

    @Builder
    private Residence(String residenceName, LocalDateTime residenceDepartureTime, LocalDateTime residenceArrivalTime) {
        this.residenceName = residenceName;
        this.residenceDepartureTime = residenceDepartureTime;
        this.residenceArrivalTime = residenceArrivalTime;
    }
}
