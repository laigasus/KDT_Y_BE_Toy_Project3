package com.example.kdt_y_be_toy_project2.domain.comment.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Optional;

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
                Optional.ofNullable(tripComment.getUpdatedAt())
                        .map(TimeUtils::formatDateTime)
                        .orElse(TimeUtils.formatDateTime(LocalDateTime.now()))
        );
    }
}