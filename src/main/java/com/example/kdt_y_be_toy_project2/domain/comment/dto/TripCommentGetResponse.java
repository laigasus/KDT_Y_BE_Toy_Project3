package com.example.kdt_y_be_toy_project2.domain.comment.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.domain.trip.entity.Trip;
import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TripCommentGetResponse(
        @NotNull Long tripCommentId,
        @NotNull Long tripId,
        @NotNull String tripName,
        @NotNull String userEmail,
        @NotNull String username,
        @NotNull String tripComment,
        @Future String updatedAt
) {
    public static TripCommentGetResponse fromEntity(TripComment tripComment) {
        return new TripCommentGetResponse(
                tripComment.getTripCommentId(),
                tripComment.getTrip().getTripId(),
                tripComment.getTrip().getTripName(),
                tripComment.getUser().getEmail(),
                tripComment.getUser().getUsername(),
                tripComment.getTripComment(),
                TimeUtils.formatDateTime(tripComment.getUpdatedAt())
        );
    }
}