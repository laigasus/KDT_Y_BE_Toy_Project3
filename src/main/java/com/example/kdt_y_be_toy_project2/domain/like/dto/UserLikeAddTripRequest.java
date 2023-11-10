package com.example.kdt_y_be_toy_project2.domain.like.dto;

import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;

public record UserLikeAddTripRequest(
        @NotNull Long tripId,
        @NotNull String email
) {
    public UserLike toEntity(){
        return UserLike.builder()
                .trip(Trip.builder().tripId(tripId).build())
                .user(User.builder().email(email).build())
                .build();
    }
}
