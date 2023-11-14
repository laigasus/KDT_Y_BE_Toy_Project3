package com.example.kdt_y_be_toy_project2.domain.like.dto;

import jakarta.validation.constraints.NotNull;

public record UserLikeAddTripRequest(
        @NotNull Long tripId
) {
}
