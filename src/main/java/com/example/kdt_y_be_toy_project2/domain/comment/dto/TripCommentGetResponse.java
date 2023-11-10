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
        @Positive Long tripCommentId,
        @NotNull Trip trip,
        @NotNull User user,
        @NotBlank String tripComment,
        @Future String updatedAt
) {
    public static TripCommentGetResponse fromEntity(TripComment tripComment) {
        return new TripCommentGetResponse(
                tripComment.getTripCommentId(),
                tripComment.getTrip(),
                tripComment.getUser(),
                tripComment.getTripComment(),
                TimeUtils.formatDateTime(tripComment.getUpdatedAt())
        );
    }
}