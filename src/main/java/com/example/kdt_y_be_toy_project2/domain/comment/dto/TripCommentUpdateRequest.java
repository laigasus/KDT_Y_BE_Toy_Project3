package com.example.kdt_y_be_toy_project2.domain.comment.dto;

import com.example.kdt_y_be_toy_project2.domain.comment.entity.TripComment;
import jakarta.validation.constraints.NotBlank;

public record TripCommentUpdateRequest(
        @NotBlank String tripComment
) {
    public TripComment toEntity() {
        return TripComment.builder()
                .tripComment(tripComment)
                .build();
    }
}
