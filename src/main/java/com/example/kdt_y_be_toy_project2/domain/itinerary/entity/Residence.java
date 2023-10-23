package com.example.kdt_y_be_toy_project2.domain.itinerary.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "체류지 장소명은 비어있을 수 없습니다")
    private String residenceName;

    @Comment("체류지 출발일시")
    @Future(message ="체류지 출발일시는 미래 날짜여야 합니다" )
    @NotNull(message = "체류지 출발일시는 비어있을 수 없습니다")
    private LocalDateTime residenceDepartureTime;


    @Comment("체류지 도착일시")
    @Future(message ="체류지 도착일시는 미래 날짜여야 합니다" )
    @NotNull(message = "체류지 도착일시는 비어있을 수 없습니다")
    private LocalDateTime residenceArrivalTime;

    @Builder
    private Residence(String residenceName, LocalDateTime residenceDepartureTime, LocalDateTime residenceArrivalTime) {
        this.residenceName = residenceName;
        this.residenceDepartureTime = residenceDepartureTime;
        this.residenceArrivalTime = residenceArrivalTime;
    }
}
