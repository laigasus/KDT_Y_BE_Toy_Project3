package com.example.kdt_y_be_toy_project2.domain.like.dto;

import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;

public record UserLikeGetTripsResponse(
        @NotNull Long tripId,
        @NotBlank String tripName,
        String createdAt
) {
    public static UserLikeGetTripsResponse fromEntity(UserLike userLike){
        return new UserLikeGetTripsResponse(
                userLike.getTrip().getTripId(),
                userLike.getTrip().getTripName(),
                userLike.getCreatedAt().toString()
        );
    }
}
