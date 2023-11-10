package com.example.kdt_y_be_toy_project2.domain.like.dto;

import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;

public record UserLikeAddTripResponse(
    @NotNull Trip trip,
    @NotNull User user
) {
    public static UserLikeAddTripResponse fromEntity(UserLike userLike) {
        return new UserLikeAddTripResponse(
                userLike.getTrip(),
                userLike.getUser()
        );
    }
}
