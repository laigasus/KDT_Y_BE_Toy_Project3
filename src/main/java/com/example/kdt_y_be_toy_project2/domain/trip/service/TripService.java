package com.example.kdt_y_be_toy_project2.domain.trip.service;

import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripRequest;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripResponse;
import com.example.kdt_y_be_toy_project2.domain.trip.error.TripNotLoadedException;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    @Transactional
    public TripResponse.AllTrips insertTrip(TripRequest tripRequest) {
        return TripResponse.AllTrips.fromEntity(
                tripRepository.save(tripRequest.toEntity())
        );
    }

    public List<TripResponse.AllTrips> selectTrips() {
        return tripRepository.findAll().stream()
                .map(TripResponse.AllTrips::fromEntity)
                .toList();
    }

    public TripResponse.TripById selectTrip(long id) {
        return tripRepository.findById(id)
                .map(TripResponse.TripById::fromEntity)
                .orElseThrow(TripNotLoadedException::new);

    }

    @Transactional
    public TripResponse.TripById updateTrip(long id, TripRequest tripRequest) {
        return tripRepository.findById(id).map(trip -> {
            trip.modifyInfo(tripRequest);
            return TripResponse.TripById.fromEntity(trip);
        }).orElseThrow(TripNotLoadedException::new);
    }
}
