package com.example.kdt_y_be_toy_project2.domain.trip.service;

import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripRequest;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripResponse;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Transactional
    public TripResponse insertTrip(TripRequest tripRequest) {
        return TripResponse.fromEntity(
                tripRepository.save(tripRequest.toEntity())
        );
    }

    public List<TripResponse> selectTrips() {
        return tripRepository.findAll().stream()
                .map(TripResponse::fromEntity)
                .toList();
    }

    public TripResponse selectTrip(long id) {
        return tripRepository.findById(id)
                .map(TripResponse::fromEntity)
                .orElse(null);
    }

    @Transactional
    public TripResponse updateTrip(long id, TripRequest tripRequest) {
        return tripRepository.findById(id).map(trip -> {
            trip.modifyInfo(tripRequest);
            return TripResponse.fromEntity(trip);
        }).orElse(null);
    }
}
