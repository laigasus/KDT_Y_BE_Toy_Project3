package com.example.kdt_y_be_toy_project2.domain.comment.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TripCommentResponse(

        @NotNull
        Long tripCommentId,
        @NotBlank
        String tripComment,
        @NotNull
        Long userId,
        @NotNull
        String name
) {
    public static TripCommentResponse fromEntity(TripComment tripcomment) {
        return new TripCommentResponse(
                tripcomment.getTripCommentId(),
                tripcomment.getTripComment(),
                tripcomment.getUser().getUserId(),
                tripcomment.getUser().getUsername()
        );
    }
}
