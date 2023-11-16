package com.example.kdt_y_be_toy_project2.domain.itinerary.entity;

import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Residence {

    @Comment("체류지")
    private String residenceName;

    @Embedded
    @AttributeOverride(name = "startTime", column = @Column(name = "stay_time"))
    @AttributeOverride(name = "endTime", column = @Column(name = "leave_time"))
    private TimeSchedule residenceTimeSchedule;

    @Builder
    private Residence(String residenceName, TimeSchedule residenceTimeSchedule) {
        this.residenceName = residenceName;
        this.residenceTimeSchedule = residenceTimeSchedule;
    }
}
