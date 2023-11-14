package com.example.kdt_y_be_toy_project2.domain.like.dto;

import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;

public record UserLikeAddTripResponse(
    @NotNull Long tripId,
    @NotNull Long userId,
    String message
) {
    public static UserLikeAddTripResponse fromEntity(UserLike userLike,String message) {
        return new UserLikeAddTripResponse(
                userLike.getTrip().getTripId(),
                userLike.getUser().getUserId(),
                message
        );
    }
}
