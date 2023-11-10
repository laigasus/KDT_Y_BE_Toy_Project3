package com.example.kdt_y_be_toy_project2.domain.trip.entity;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripRequest;
import com.example.kdt_y_be_toy_project2.global.entity.BaseTimeEntity;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Comment("여행")
public class Trip extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("여행 번호(PK)")
    private Long tripId;

    @Comment("여행 이름")
    private String tripName;

    @Embedded
    private TimeSchedule timeSchedule;

    @Enumerated(EnumType.STRING)
    @Comment("여행 국내/외 여부")
    private TripDestinationEnum tripDestinationEnum;

    @OneToMany(mappedBy = "trip")
    private final List<Itinerary> itineraries = new ArrayList<>();

    @OneToMany(mappedBy = "trip")
    private final List<TripComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "trip")
    private final List<UserLike> userLikes = new ArrayList<>();

    @Builder
    private Trip(Long tripId, String tripName, TimeSchedule timeSchedule, TripDestinationEnum tripDestinationEnum) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.timeSchedule = timeSchedule;
        this.tripDestinationEnum = tripDestinationEnum;
    }

    public void modifyInfo(TripRequest tripRequest) {
        this.tripName = tripRequest.tripName();
        this.timeSchedule = tripRequest.timeSchedule();
        this.tripDestinationEnum = tripRequest.tripDestinationEnum();
    }
}
