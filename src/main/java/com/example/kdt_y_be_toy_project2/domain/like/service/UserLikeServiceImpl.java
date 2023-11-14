package com.example.kdt_y_be_toy_project2.domain.like.service;

import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeGetTripsResponse;
import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.domain.like.repository.UserLikeRepository;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.trip.error.TripNotLoadedException;
import com.example.kdt_y_be_toy_project2.domain.trip.repository.TripRepository;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLikeServiceImpl implements UserLikeService {

    private final UserLikeRepository userLikeRepository;
    private final TripRepository tripRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserLikeGetTripsResponse> bringUserLike(User user) {

        return userLikeRepository.findByUser(user).stream()
                .map(UserLikeGetTripsResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public Long addUserLike(User user, Long tripId) {

        Optional<Trip> tripOptional = tripRepository.findById(tripId);

        if (tripOptional.isEmpty()) {
            throw new TripNotLoadedException();
        }

        UserLike like = UserLike.like(user, tripOptional.get());
        userLikeRepository.save(like);

        return like.getUserLikeId();
    }

    @Override
    @Transactional
    public void removeUserLike(User user, Long tripId) {

        Optional<UserLike> userLike = userLikeRepository.findByUserAndTripTripId(user, tripId);

        if (userLike.isEmpty()) {
            throw new TripNotLoadedException();
        }

        userLike.get().delete();

        userLikeRepository.delete(userLike.get());
    }
}
