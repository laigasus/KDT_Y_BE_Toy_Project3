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
public class Activity {
    @Enumerated(EnumType.STRING)
    @Comment("이동수단")
    private TransportEnum transportEnum;

    @Comment("출발지")
    private String departurePlace;

    @Comment("도착지")
    private String arrivalPlace;

    @Comment("설명")
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startTime", column = @Column(name = "activity_start")),
            @AttributeOverride(name = "endTime", column = @Column(name = "activity_end"))
    })
    private TimeSchedule activityTimeSchedule;

    @Builder
    private Activity(TransportEnum transportEnum, String departurePlace, String arrivalPlace, String description, TimeSchedule activityTimeSchedule) {
        this.transportEnum = transportEnum;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.description = description;
        this.activityTimeSchedule = activityTimeSchedule;
    }
}
