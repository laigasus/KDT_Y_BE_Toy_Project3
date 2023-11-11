package com.example.kdt_y_be_toy_project2.domain.comment.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record TripCommentAddRequest(
        @NotNull
        @Size(min = 1, max = 1000, message = "size must 1-1000")
        String tripComment
) {
    public TripComment toEntity(User user,Trip trip) {
        return TripComment.builder()
                .trip(trip)
                .user(user)
                .tripComment(tripComment)
                .build();
    }
}
