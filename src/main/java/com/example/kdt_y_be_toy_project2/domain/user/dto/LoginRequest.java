package com.example.kdt_y_be_toy_project2.domain.user.dto;

import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(

        @NotNull
        String email,
        @NotNull
        String username,
        @NotNull
        String password
) {
    public User toEntity() {
        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }
}
