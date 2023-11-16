package com.example.kdt_y_be_toy_project2.domain.trip.service;

import com.example.kdt_y_be_toy_project2.domain.comment.repository.TripCommentRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripRequest;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripResponse;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.TripDestinationEnum;
import com.example.kdt_y_be_toy_project2.domain.trip.error.TripNotLoadedException;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import com.example.kdt_y_be_toy_project2.global.dummy.DummyObjectForService;
import com.example.kdt_y_be_toy_project2.global.entity.BaseTimeEntity;
import com.example.kdt_y_be_toy_project2.global.entity.TimeSchedule;
import jakarta.persistence.Temporal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


class TripServiceTest extends DummyObjectForService {

    @InjectMocks
    private TripService tripService;

    @Mock
    private TripRepository tripRepository;

    @Mock
    private TripCommentRepository tripCommentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    @DisplayName("Trip에 있는 Id 와 repsone에 Trip을 비교하는 테스트 ")
    void insertTripTest() {
         //given
        LocalDateTime startTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 1, 2, 12, 0);
        TimeSchedule timeSchedule = new TimeSchedule(startTime, endTime);

        TripRequest tripRequest = new TripRequest(
                "강원도 여행",
                timeSchedule,
                TripDestinationEnum.DOMESTIC,
                null // itineraries는 이 테스트에선 중요하지 않으므로 null 처리
        );

        Trip trip = dummyTrip();

        given(tripRepository.save(any(Trip.class))).willReturn(trip);
        System.out.println(trip.toString());

         //when
        TripResponse.AllTrips response = tripService.insertTrip(tripRequest);

         //then
        assertNotNull(response);
        assertNotNull(response.tripInfo().createdAt());
        assertNotNull(response.tripInfo().updatedAt());
        assertEquals(trip.getTripId(),response.tripInfo().tripId());


    }


    @Test
    @DisplayName("trip들의 갯수가 맞는지 검사")
    void selectTripTest() {
        //given
        List<Trip> trips = List.of(dummyTrip());
        given(tripRepository.findAll()).willReturn(trips);


        //when
        System.out.println(tripService.selectTrips().toString());
        List<TripResponse.AllTrips> responses = tripService.selectTrips();


        //then
        assertNotNull(responses);
        assertEquals(trips.size(),responses.size());

    }

    @ParameterizedTest
    @DisplayName("존재하지않는 Id 조회시 TripNotLoadedException 발생")
    @ValueSource(longs = {999,3000,500,400,300,222})
    void selectTripTest_WhenTripNotFound(long value) {
        // given
        when(tripRepository.findById(value)).thenReturn(Optional.empty());

        // then
        assertThrows(TripNotLoadedException.class, () -> {
            tripService.selectTrip(value);
        });
    }

}
