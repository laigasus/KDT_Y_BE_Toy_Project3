package com.example.kdt_y_be_toy_project2.domain.like.service;

import com.example.kdt_y_be_toy_project2.domain.like.repository.UserLikeRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import com.example.kdt_y_be_toy_project2.global.dummy.DummyObjectForController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;

class UserLikeServiceImplTest extends DummyObjectForController {
    @Mock
    UserLikeRepository userLikeRepository;
    @Mock
    TripRepository tripRepository;
    @InjectMocks
    UserLikeServiceImpl userLikeServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBringUserLike() {
        // given
        given(userLikeRepository.findByUser(any())).willReturn(List.of(dummyUserLike()));

        // when
        var actual = userLikeServiceImpl.bringUserLike(dummyPrincipalDetails());

        // then
        var expected = List.of(dummyUserLikeGetTripsResponse());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddUserLike() {
        // given
        given(tripRepository.findById(anyLong())).willReturn(Optional.of(dummyTrip()));
        given(userLikeRepository.findByUserAndTripTripId(any(), anyLong())).willReturn(Optional.of(dummyUserLike()));

        // when
        var actual = userLikeServiceImpl.addUserLike(dummyPrincipalDetails(), 1L);

        // then
        var expected = dummyUserLikeAddTripResponse();
        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    void testRemoveUserLike() {
        // given
        given(userLikeRepository.findByUserAndTripTripId(any(), anyLong())).willReturn(Optional.of(dummyUserLike()));

        // when
        var actual = userLikeServiceImpl.removeUserLike(dummyPrincipalDetails(), 1L);

        // then
        var expected = dummyDeleteUserLikeResponse();
        Assertions.assertEquals(expected, actual);
    }
}