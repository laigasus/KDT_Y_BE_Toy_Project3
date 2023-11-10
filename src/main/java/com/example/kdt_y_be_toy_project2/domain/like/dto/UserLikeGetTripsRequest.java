package com.example.kdt_y_be_toy_project2.domain.like.dto;

import jakarta.validation.constraints.NotNull;

public record UserLikeGetTripsRequest(
        @NotNull Long tripId,
        @NotNull String email
) {
}
