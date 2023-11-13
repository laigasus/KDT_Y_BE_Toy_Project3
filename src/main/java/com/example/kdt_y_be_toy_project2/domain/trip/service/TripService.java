package com.example.kdt_y_be_toy_project2.domain.trip.service;

import com.example.kdt_y_be_toy_project2.domain.comment.dto.TripCommentGetResponse;
import com.example.kdt_y_be_toy_project2.domain.comment.repository.TripCommentRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripRequest;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripResponse;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.error.TripNotLoadedException;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final TripCommentRepository tripCommentRepository;

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
        Optional<Trip> tripOptional = tripRepository.findById(id);

        if (tripOptional.isEmpty()) {
            throw new TripNotLoadedException();
        }

        Trip trip = tripOptional.get();
        List<TripCommentGetResponse> tripCommentGetResponses =
                tripCommentRepository.findByTripTripId(id).stream()
                        .map(TripCommentGetResponse::fromEntity)
                        .toList();

        return TripResponse.TripById.fromEntity(trip, tripCommentGetResponses);
    }

    @Transactional
    public TripResponse.TripById updateTrip(long id, TripRequest tripRequest) {
        List<TripCommentGetResponse> tripCommentGetResponses =
                tripCommentRepository.findByTripTripId(id).stream()
                        .map(TripCommentGetResponse::fromEntity)
                        .toList();

        return tripRepository.findById(id).map(trip -> {
            trip.modifyInfo(tripRequest);
            return TripResponse.TripById.fromEntity(trip, tripCommentGetResponses);
        }).orElseThrow(TripNotLoadedException::new);
    }
}
