package com.example.kdt_y_be_toy_project2.domain.user.controller;

import com.example.kdt_y_be_toy_project2.domain.user.error.EmailDuplicateError;
import com.example.kdt_y_be_toy_project2.global.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(value = {
            EmailDuplicateError.class
    })
    public ResponseEntity<ErrorMessage> handleEmailDuplicateError(
            EmailDuplicateError exception
    ) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
