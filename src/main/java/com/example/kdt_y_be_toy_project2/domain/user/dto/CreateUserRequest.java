package com.example.kdt_y_be_toy_project2.domain.user.dto;

import com.example.kdt_y_be_toy_project2.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record CreateUserRequest (
        @NotNull
        String email,

        @NotNull
        String username,

        @NotNull
        String password
){
    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User toEntity(){
        return User.builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
