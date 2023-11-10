package com.example.kdt_y_be_toy_project2.domain.like.service;

import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeAddTripRequest;
import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeAddTripResponse;
import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeGetTripsResponse;
import com.example.kdt_y_be_toy_project2.domain.like.dto.UserLikeRemoveTripRequest;
import com.example.kdt_y_be_toy_project2.domain.like.repository.UserLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLikeServiceImpl implements UserLikeService {

    private final UserLikeRepository userLikeRepository;

    public List<UserLikeGetTripsResponse> bringUserLike(long userId){
        return userLikeRepository.findById(userId).stream()
                .map(UserLikeGetTripsResponse::fromEntity)
                .toList();
    }

    public UserLikeAddTripResponse addUserLike(UserLikeAddTripRequest userLikeAddTripRequest){
        return UserLikeAddTripResponse.fromEntity(
                userLikeRepository.save(userLikeAddTripRequest.toEntity())
        );
    }

    public boolean removeUserLike(UserLikeRemoveTripRequest likeRemoveTripRequest){
        return userLikeRepository.deleteByUserEmailAndTripTripId(
                likeRemoveTripRequest.email(),
                likeRemoveTripRequest.tripId()
        );

    }
}
