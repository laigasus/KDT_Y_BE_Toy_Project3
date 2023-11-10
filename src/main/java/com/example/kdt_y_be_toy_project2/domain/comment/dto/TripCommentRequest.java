package com.example.kdt_y_be_toy_project2.domain.comment.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TripCommentRequest(

        @NotNull
        Trip trip,

        @NotNull
        @Size(min = 1, max = 500, message = "size must 1-500")
        String tripComment,

        @NotNull
        User user
) {
    public TripComment toEntity() {
        return TripComment.builder()
                .trip(trip)
                .tripComment(tripComment)
                .user(user)
                .build();
    }
}
