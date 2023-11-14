package com.example.kdt_y_be_toy_project2.domain.like.dto;

import jakarta.validation.constraints.NotNull;

public record DeleteUserLikeResponse(
        String message
) {
    public static DeleteUserLikeResponse fromEntity(String message){
        return new DeleteUserLikeResponse(message);
    }
}
