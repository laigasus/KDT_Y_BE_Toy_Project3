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
public class Accommodation {
    @Comment("숙소명")
    private String accommodationName;

    @Embedded
    @AttributeOverride(name = "startTime", column = @Column(name = "check_in"))
    @AttributeOverride(name = "endTime", column = @Column(name = "check_out"))
    private TimeSchedule accommodationTimeSchedule;

    @Builder
    private Accommodation(String accommodationName, TimeSchedule accommodationTimeSchedule) {
        this.accommodationName = accommodationName;
        this.accommodationTimeSchedule = accommodationTimeSchedule;
    }
}
