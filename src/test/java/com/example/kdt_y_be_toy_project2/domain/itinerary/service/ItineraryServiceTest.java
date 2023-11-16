package com.example.kdt_y_be_toy_project2.domain.itinerary.service;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.repository.ItineraryRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyLong;

class ItineraryServiceTest extends DummyObjectForController {
    @Mock
    ItineraryRepository itineraryRepository;
    @Mock
    TripRepository tripRepository;
    @InjectMocks
    ItineraryService itineraryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertItineraries() {
        // given
        given(tripRepository.findById(anyLong())).willReturn(Optional.of(dummyTrip()));
        given(itineraryRepository.saveAll(anyList())).willReturn(List.of(dummyItinerary()));

        // when
        var actual = itineraryService.insertItineraries(1L, List.of(dummyItineraryRequest()));

        // then
        assertFalse(actual.isEmpty());
    }

    @Test
    void testSelectItineraries() {
        // given
        given(itineraryRepository.findByTripTripId(anyLong())).willReturn(List.of(dummyItinerary()));

        // when
        var actual = itineraryService.selectItineraries(1L);

        // then
        assertFalse(actual.isEmpty());
    }

    @Test
    void testSelectItinerary() {
        // given
        given(itineraryRepository.findById(anyLong())).willReturn(Optional.of(dummyItinerary()));

        // when
        var actual = itineraryService.selectItinerary(1L);

        // then
        var expect = dummyItineraryResponse();
        assertEquals(expect.id(), actual.id());
        assertEquals(expect.itineraryName(), actual.itineraryName());
        assertEquals(expect.tripId(), actual.tripId());
    }

    @Test
    void testUpdateItinerary() {
        // given
        given(itineraryRepository.findById(anyLong())).willReturn(Optional.of(dummyItinerary()));

        // when
        var actual = itineraryService.updateItinerary(1L, dummyItineraryRequest());

        // then
        var expect = dummyItineraryResponse();

        assertEquals(expect.id(), actual.id());
        assertEquals(expect.itineraryName(), actual.itineraryName());
        assertEquals(expect.tripId(), actual.tripId());
    }



}