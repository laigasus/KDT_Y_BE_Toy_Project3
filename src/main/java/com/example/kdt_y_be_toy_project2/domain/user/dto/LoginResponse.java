package com.example.kdt_y_be_toy_project2.domain.user.dto;

import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;

public record LoginResponse(
        @NotNull
        String email,
        @NotNull
        String name

){
    public static LoginResponse fromEntity(User user){
        return new LoginResponse(
                user.getEmail(),
                user.getUsername()
        );
    }
}
