package com.example.kdt_y_be_toy_project2.global.exception;

import java.time.LocalDateTime;

public record ErrorMessage(
        String message,
        LocalDateTime timestamp
) {

}
