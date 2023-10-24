package com.example.kdt_y_be_toy_project2.domain.itinerary.service;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryRequest;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.entity.Itinerary;
import com.example.kdt_y_be_toy_project2.domain.itinerary.repository.ItineraryRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final EntityManager em;

    // 여정 다건 등록

    /*
        TODO
        TripRepository 구현 후에
        여정 다건 등록 수정
     */
    @Transactional
    public List<ItineraryResponse> insertItineraries(List<ItineraryRequest> itineraryRequestList) {

        Trip trip = Trip.builder().build();
        em.persist(trip);

        List<Itinerary> itineraries = itineraryRequestList.stream()
                .map(ItineraryRequest::toEntity)
                .peek(itinerary -> itinerary.setTrip(trip))
                .toList();

        return itineraries.stream()
                .map(ItineraryResponse::fromEntity)
                .collect(Collectors.toList());
    }


    // 전체 여정 조회
    public List<ItineraryResponse> selectItineraries() {
        return itineraryRepository.findAll().stream()
                .map(ItineraryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // TripId로 여정 다건 조회
    public List<ItineraryResponse> selectItineraries(Long tripId) {
        return itineraryRepository.findByTripId(tripId).stream()
                .map(ItineraryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // TripId로 여정 단건 조회
    public ItineraryResponse selectItinerary(Long itineraryId) {
        return itineraryRepository.findById(itineraryId)
                .map(ItineraryResponse::fromEntity)
                .orElse(null);
    }

    // 여정 수정
    @Transactional
    public ItineraryResponse updateItinerary(long id, ItineraryRequest itineraryRequest) {
        return itineraryRepository.findById(id).map(itinerary -> {
            itinerary.modifyInfo(itineraryRequest);
            return ItineraryResponse.fromEntity(itinerary);
        }).orElse(null);
    }

}
