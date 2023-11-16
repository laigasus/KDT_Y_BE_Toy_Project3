package com.example.kdt_y_be_toy_project2.domain.comment.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Optional;

public record TripCommentAddResponse(
        @Positive Long tripCommentId,
        @NotNull Long tripId,
        @NotNull Long userId,
        @NotNull String username,
        @NotBlank String tripComment,
        @Future String createdAt
) {
    public static TripCommentAddResponse fromEntity(TripComment tripComment) {
        return new TripCommentAddResponse(
                tripComment.getTripCommentId(),
                tripComment.getTrip().getTripId(),
                tripComment.getUser().getUserId(),
                tripComment.getUser().getUsername(),
                tripComment.getTripComment(),
                Optional.ofNullable(tripComment.getCreatedAt())
                        .map(TimeUtils::formatDateTime)
                        .orElse(TimeUtils.formatDateTime(LocalDateTime.now()))
        );
    }
}
