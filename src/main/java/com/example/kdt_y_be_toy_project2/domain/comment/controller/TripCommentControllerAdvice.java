package com.example.kdt_y_be_toy_project2.domain.comment.controller;

import com.example.kdt_y_be_toy_project2.domain.comment.error.InvalidAccessToUpdateTripComment;
import com.example.kdt_y_be_toy_project2.domain.comment.error.InvalidTripCommentIdException;
import com.example.kdt_y_be_toy_project2.domain.comment.error.InvalidTripException;
import com.example.kdt_y_be_toy_project2.global.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TripCommentControllerAdvice {

    @ExceptionHandler(value = {
            InvalidAccessToUpdateTripComment.class
    })
    public ResponseEntity<ErrorMessage> handleInvalidAccessToUpdateTripComment(
            InvalidAccessToUpdateTripComment exception
    ) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            InvalidTripCommentIdException.class
    })
    public ResponseEntity<ErrorMessage> handleInvalidTripCommentIdException(
            InvalidTripCommentIdException exception
    ) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            InvalidTripException.class
    })
    public ResponseEntity<ErrorMessage> handleInvalidTripException(
            InvalidTripException exception
    ) {
        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
