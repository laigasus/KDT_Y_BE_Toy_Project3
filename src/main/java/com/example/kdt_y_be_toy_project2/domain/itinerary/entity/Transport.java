package com.example.kdt_y_be_toy_project2.domain.itinerary.entity;

import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Transport {
    @Enumerated(EnumType.STRING)
    @Comment("이동수단")
    private TransportEnum transportEnum;

    @Comment("출발지")
    private String departurePlace;

    @Comment("도착지")
    private String arrivalPlace;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startTime", column = @Column(name = "departure_time")),
            @AttributeOverride(name = "endTime", column = @Column(name = "arrival_time"))
    })
    private TimeSchedule transportTimeSchedule;

    @Builder
    private Transport(TransportEnum transportEnum, String departurePlace, String arrivalPlace, TimeSchedule transportTimeSchedule) {
        this.transportEnum = transportEnum;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.transportTimeSchedule = transportTimeSchedule;
    }
}
