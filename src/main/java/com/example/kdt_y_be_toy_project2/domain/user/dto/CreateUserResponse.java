package com.example.kdt_y_be_toy_project2.domain.user.dto;

import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;

public record CreateUserResponse(
        @NotNull
        String email,

        @NotNull
        String name

){
    public static CreateUserResponse fromEntity(User user){
        return new CreateUserResponse(
                user.getEmail(),
                user.getUsername()
        );
    }
}
