package com.example.kdt_y_be_toy_project2.domain.comment.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import com.example.kdt_y_be_toy_project2.global.util.TimeUtils;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Optional;

public record TripCommentUpdateResponse(
        @Positive Long tripCommentId,
        @NotNull Long tripId,
        @NotNull Long userId,
        @NotBlank String tripComment,
        @Future String createdAt
) {
    public static TripCommentUpdateResponse fromEntity(TripComment tripComment) {
        return new TripCommentUpdateResponse(
                tripComment.getTripCommentId(),
                tripComment.getTrip().getTripId(),
                tripComment.getUser().getUserId(),
                tripComment.getTripComment(),
                Optional.ofNullable(tripComment.getUpdatedAt())
                        .map(TimeUtils::formatDateTime)
                        .orElse(TimeUtils.formatDateTime(LocalDateTime.now()))
        );
    }
}
