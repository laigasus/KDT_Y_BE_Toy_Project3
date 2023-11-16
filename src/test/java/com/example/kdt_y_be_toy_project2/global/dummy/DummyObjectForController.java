package com.example.kdt_y_be_toy_project2.global.dummy;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.*;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryRequest;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.AccommodationDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ActivityDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.sub.ResidenceDTO;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.TransportEnum;
import com.example.kdt_y_be_toy_project2.domain.like.dto.*;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripKeyWord;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripRequest;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripResponse;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserRequest;
import com.example.kdt_y_be_toy_project2.domain.user.dto.CreateUserResponse;
import com.example.kdt_y_be_toy_project2.domain.user.dto.LoginRequest;
import com.example.kdt_y_be_toy_project2.domain.user.dto.LoginResponse;
import com.example.kdt_y_be_toy_project2.global.dto.TimeScheduleDTO;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class DummyObjectForController extends DummyObjectForService{
    // TripComment
    protected TripCommentAddRequest dummyTripCommentAddRequest() {
        return new TripCommentAddRequest(
                "댓글 내용"
        );
    }

    protected TripCommentAddResponse dummyTripCommentAddResponse() {
        return new TripCommentAddResponse(
                1L,
                1L,
                1L,
                "liyusang1",
                "즐거운여행입니다. 여행테스트",
                TimeUtils.formatDateTime(LocalDateTime.now())
        );
    }

    protected TripCommentGetResponse dummyTripCommentGetResponse() {
        return new TripCommentGetResponse(
                1L,
                1L,
                "부산 여행",
                "liyusang1@naver.com",
                "liyusang1",
                "즐거운여행입니다. 여행테스트",
                TimeUtils.formatDateTime(LocalDateTime.now())
        );
    }

    protected TripCommentUpdateRequest dummyTripCommentUpdateRequest() {
        return new TripCommentUpdateRequest(
                "즐거운여행입니다. 댓글수정합니다. 12:56"
        );
    }

    protected TripCommentUpdateResponse dummyTripCommentUpdateResponse() {
        return new TripCommentUpdateResponse(
                1L,
                1L,
                1L,
                "즐거운여행입니다. 댓글수정합니다. 12:56",
                "2023년 11월 16일 10시 31분"
        );
    }


    // itinerary
    protected ItineraryRequest dummyItineraryRequest() {
        return new ItineraryRequest(
                "부산 여정",
                List.of(dummyAccommodation()),
                List.of(dummyResidence()),
                List.of(dummyActivity()),
                dummyTimeSchedule()
        );
    }

    protected ItineraryResponse dummyItineraryResponse() {
        return new ItineraryResponse(
                1L,
                "부산 여정",
                1L,
                List.of(dummyAccommodationDTO()),
                List.of(dummyResidenceDTO()),
                List.of(dummyActivityDTO()),
                dummyTimeScheduleDTO(),
                TimeUtils.formatDateTime(LocalDateTime.now()),
                TimeUtils.formatDateTime(LocalDateTime.now())
        );
    }

    protected AccommodationDTO dummyAccommodationDTO() {
        return new AccommodationDTO(
                "신라 스테이 해운대",
                TimeUtils.formatDateTime(LocalDateTime.now()),
                TimeUtils.formatDateTime(LocalDateTime.now())
        );
    }

    protected ResidenceDTO dummyResidenceDTO() {
        return new ResidenceDTO(
                "부산",
                TimeUtils.formatDateTime(LocalDateTime.now()),
                TimeUtils.formatDateTime(LocalDateTime.now())
        );
    }

    protected ActivityDTO dummyActivityDTO() {
        return new ActivityDTO(
                TransportEnum.KTX.getMethod(),
                "집",
                "부산역",
                "KTX타고 드디어 부산입성!",
                TimeUtils.formatDateTime(LocalDateTime.now()),
                TimeUtils.formatDateTime(LocalDateTime.now()),
                "Jungang-daero, 초량제3동 Dong-gu, Busan, South Korea"
        );
    }

    protected TimeScheduleDTO dummyTimeScheduleDTO() {
        return new TimeScheduleDTO(
                TimeUtils.formatDate(LocalDateTime.now()),
                TimeUtils.formatDate(LocalDateTime.now())
        );
    }

    // like

    protected UserLikeAddTripRequest dummyUserLikeAddTripRequest() {
        return new UserLikeAddTripRequest(
                1L
        );
    }

    protected UserLikeAddTripResponse dummyUserLikeAddTripResponse() {
        return new UserLikeAddTripResponse(
                1L,
                1L,
                "1번 여행 좋아요 요청 성공"
        );
    }

    protected UserLikeGetTripsResponse dummyUserLikeGetTripsResponse() {
        return new UserLikeGetTripsResponse(
                1L,
                "부산 여행",
                TimeUtils.formatDateTime(LocalDateTime.now())
        );
    }

    protected UserLikeRemoveTripRequest dummyUserLikeRemoveTripRequest() {
        return new UserLikeRemoveTripRequest(
                1L
        );
    }

    protected DeleteUserLikeResponse dummyDeleteUserLikeResponse() {
        return new DeleteUserLikeResponse(
                "좋아요 삭제 성공"
        );
    }

    // trip
    protected TripKeyWord dummyTripKeyWord() {
        return new TripKeyWord(
                "부산"
        );
    }

    protected TripRequest dummyTripRequest() {
        return new TripRequest(
                "부산 여행",
                dummyTimeSchedule(),
                TripDestinationEnum.DOMESTIC,
                Collections.emptyList()
        );
    }

    protected TripResponse.AllTrips dummyTripResponseAllTrips() {
        return new TripResponse.AllTrips(
                dummyTripResponseTripInfo(),
                List.of("부산 여정")
        );
    }

    protected TripResponse.TripById dummyTripResponseTripById() {
        return new TripResponse.TripById(
                dummyTripResponseTripInfo(),
                List.of(dummyItineraryResponse()),
                List.of(dummyTripCommentGetResponse())
        );
    }

    protected TripResponse.TripByKeyWord dummyTripResponseTripByKeyWord() {
        return new TripResponse.TripByKeyWord(
                1L,
                "부산 여행",
                TimeUtils.formatDateTime(LocalDateTime.now()),
                1
        );
    }

    protected TripResponse.TripInfo dummyTripResponseTripInfo() {
        return new TripResponse.TripInfo(
                1L,
                "부산 여행",
                dummyTimeScheduleDTO(),
                TripDestinationEnum.DOMESTIC.getMethod(),
                TimeUtils.formatDateTime(LocalDateTime.now()),
                TimeUtils.formatDateTime(LocalDateTime.now()),
                1
        );
    }

    // user
    protected CreateUserRequest dummyCreateUserRequest() {
        return new CreateUserRequest(
                "liyusang1@naver.com",
                "liyusang1",
                "123456"
        );
    }

    protected CreateUserRequest dummyInvalidUserRequest() {
        return new CreateUserRequest(
                "test@Test@Gmal.com",
                "1231233123",
                "jaxking"
        );
    }


    protected CreateUserResponse dummyCreateUserResponse() {
        return new CreateUserResponse(
                "liyusang1@naver.com",
                "liyusang1"
        );
    }

    protected LoginRequest dummyUserLoginRequest() {
        return new LoginRequest(
                "liyusang1",
                "123456"
        );
    }

    protected LoginResponse dummyUserLoginResponse() {
        return new LoginResponse(
                "liyusang1@naver.com",
                "liyusang1"
        );
    }
}