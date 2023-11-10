package com.example.kdt_y_be_toy_project2.domain.comment.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TripCommentUpdateRequest(
        @NotNull Trip trip,
        @NotBlank User user,
        @NotBlank String tripComment
) {
    public TripComment toEntity() {
        return TripComment.builder()
                .trip(trip)
                .user(user)
                .tripComment(tripComment)
                .build();
    }
}