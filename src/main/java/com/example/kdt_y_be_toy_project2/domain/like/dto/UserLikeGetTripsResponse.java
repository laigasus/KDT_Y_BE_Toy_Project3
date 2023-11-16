package com.example.kdt_y_be_toy_project2.domain.like.dto;

import com.example.kdt_y_be_toy_project2.domain.like.entity.UserLike;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Optional;

public record UserLikeGetTripsResponse(
        @NotNull Long tripId,
        @NotBlank String tripName,
        String createdAt
) {
    public static UserLikeGetTripsResponse fromEntity(UserLike userLike) {
        return new UserLikeGetTripsResponse(
                userLike.getTrip().getTripId(),
                userLike.getTrip().getTripName(),
                Optional.ofNullable(userLike.getCreatedAt())
                        .map(LocalDateTime::toString)
                        .orElse(TimeUtils.formatDateTime(LocalDateTime.now()))
        );
    }
}
