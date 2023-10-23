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
public class Accommodation {
    @Comment("숙박 숙소명")
    private String accommodationName;

    @Comment("체크인 일시")
    private LocalDateTime accommodationCheckIn;

    @Comment("체크아웃 일시")
    private LocalDateTime accommodationCheckOut;

    @Builder
    private Accommodation(String accommodationName, LocalDateTime accommodationCheckIn, LocalDateTime accommodationCheckOut) {
        this.accommodationName = accommodationName;
        this.accommodationCheckIn = accommodationCheckIn;
        this.accommodationCheckOut = accommodationCheckOut;
    }
}
