package com.example.kdt_y_be_toy_project2.global.dummy;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.*;
import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import com.example.kdt_y_be_toy_project2.global.security.PrincipalDetails;

import java.time.LocalDateTime;
import java.util.List;

public class DummyObjectForService {
    protected static User dummyUser() {
        return User.builder()
                .email("liyusang1@naver.com")
                .username("liyusang1")
                .password("123456")
                .authority("ROLE_USER")
                .build();
    }

    protected Trip dummyTrip() {
        return Trip.builder()
                .tripId(1L) //
                .tripName("부산 여행")
                .timeSchedule(dummyTimeSchedule())
                .tripDestinationEnum(TripDestinationEnum.DOMESTIC)
                .build();
    }

    protected Itinerary dummyItinerary() {
        return Itinerary.builder()
                .itineraryId(1L)
                .itineraryName("부산 여정")
                .trip(dummyTrip())
                .timeSchedule(dummyTimeSchedule())
                .accommodations(List.of(dummyAccommodation()))
                .residences(List.of(dummyResidence()))
                .activities(List.of(dummyActivity()))
                .build();
    }

    protected TripComment dummyTripComment() {
        return TripComment.builder()
                .tripCommentId(1L)
                .trip(dummyTrip())//
                .user(dummyUser())//
                .tripComment("즐거운여행입니다. 여행테스트")
                .build();
    }

    protected UserLike dummyUserLike() {
        return UserLike.builder()
                .user(dummyUser())
                .trip(dummyTrip())
                .build();
    }

    protected PrincipalDetails dummyPrincipalDetails() {
        return new PrincipalDetails(
                dummyUser(),
                "liyusang1@naver.com",
                "123456"
        );
    }

    protected TimeSchedule dummyTimeSchedule() {
        return new TimeSchedule(
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    protected Accommodation dummyAccommodation() {
        return Accommodation.builder()
                .accommodationName("신라 스테이 해운대")
                .accommodationTimeSchedule(dummyTimeSchedule())
                .build();
    }

    protected Residence dummyResidence() {
        return Residence.builder()
                .residenceName("부산")
                .residenceTimeSchedule(dummyTimeSchedule())
                .build();
    }

    protected Activity dummyActivity() {
        return Activity.builder()
                .transportEnum(TransportEnum.KTX)
                .departurePlace("집")
                .arrivalPlace("부산역")
                .description("KTX타고 드디어 부산입성!")
                .activityTimeSchedule(dummyTimeSchedule())
                .build();
    }
}
