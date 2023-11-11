package com.example.kdt_y_be_toy_project2.domain.comment.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;

import java.util.Optional;

public record TripCommentAddRequest(
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
