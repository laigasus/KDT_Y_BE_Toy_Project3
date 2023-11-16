package com.example.kdt_y_be_toy_project2.global.dummy;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.*;
import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;

import java.time.LocalDateTime;
import java.util.List;

public class DummyObjectForRepository {
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
                .tripName("부산 여행 일정 수정")
                .timeSchedule(dummyTimeSchedule())
                .tripDestinationEnum(TripDestinationEnum.DOMESTIC)
                .build();
    }

    protected Itinerary dummyItinerary(Trip trip) {
        return Itinerary.builder()
                .itineraryName("부산 여정")
                .trip(trip)
                .timeSchedule(dummyTimeSchedule())
                .accommodations(List.of(dummyAccommodation()))
                .residences(List.of(dummyResidence()))
                .activities(List.of(dummyActivity()))
                .build();
    }

    protected TripComment dummyTripComment(Trip trip, User user) {
        return TripComment.builder()
                .trip(trip)//
                .user(user)//
                .tripComment("즐거운여행입니다. 여행테스트")
                .build();
    }

    protected UserLike dummyUserLike(User user, Trip trip) {
        return UserLike.builder()
                .user(user)
                .trip(trip)
                .build();
    }

    private TimeSchedule dummyTimeSchedule() {
        return new TimeSchedule(
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private Accommodation dummyAccommodation() {
        return Accommodation.builder()
                .accommodationName("신라 스테이 해운대")
                .accommodationTimeSchedule(dummyTimeSchedule())
                .build();
    }

    private Residence dummyResidence() {
        return Residence.builder()
                .residenceName("부산")
                .residenceTimeSchedule(dummyTimeSchedule())
                .build();
    }

    private Activity dummyActivity() {
        return Activity.builder()
                .transportEnum(TransportEnum.KTX)
                .departurePlace("집")
                .arrivalPlace("부산역")
                .description("KTX타고 드디어 부산입성!")
                .arrivalAddress("Jungang-daero, 초량제3동 Dong-gu, Busan, South Korea")
                .activityTimeSchedule(dummyTimeSchedule())
                .build();
    }
}
