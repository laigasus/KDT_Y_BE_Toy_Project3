package com.example.kdt_y_be_toy_project2.domain.trip.entity;

import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripRequest;
import com.example.kdt_y_be_toy_project2.global.entity.BaseTimeEntity;
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
public class Trip extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id", updatable = false, nullable = false)
    @Comment("여행 번호(PK)")
    private Long id;

    @Comment("여행 이름")
    private String tripName;

    @Embedded
    private TripSchedule tripSchedule;

    @Enumerated(EnumType.STRING)
    @Comment("여행 국내/외 여부")
    private TripDestinationEnum tripDestinationEnum;

    @OneToMany(mappedBy = "trip")
    private final List<Itinerary> itineraries = new ArrayList<>();

    @Builder
    private Trip(String tripName, TripSchedule tripSchedule, TripDestinationEnum tripDestinationEnum) {
        this.tripName = tripName;
        this.tripSchedule = tripSchedule;
        this.tripDestinationEnum = tripDestinationEnum;
    }

    // 여행 정보 수정
    public void modifyInfo(TripRequest tripRequest){
        this.tripName = tripRequest.getTripName();
        this.tripSchedule = tripRequest.getTripSchedule();
        this.tripDestinationEnum = tripRequest.getTripDestinationEnum();
    }
}
