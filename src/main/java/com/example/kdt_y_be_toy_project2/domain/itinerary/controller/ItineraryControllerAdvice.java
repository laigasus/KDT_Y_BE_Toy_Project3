package com.example.kdt_y_be_toy_project2.domain.itinerary.controller;


import com.example.kdt_y_be_toy_project2.domain.itinerary.error.ItineraryNotInsertedException;
import com.example.kdt_y_be_toy_project2.domain.itinerary.error.ItineraryNotLoadedException;
import com.example.kdt_y_be_toy_project2.domain.itinerary.error.ItineraryNotUpdatedException;
import com.example.kdt_y_be_toy_project2.global.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ItineraryControllerAdvice {

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> httpMessageNotReadableException() {
        ErrorMessage message = new ErrorMessage(
                "유효하지 않은 JSON 형태 입니다.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            ItineraryNotInsertedException.class,
            ItineraryNotUpdatedException.class
    })
    public ResponseEntity<ErrorMessage> itineraryNotInsertedOrUpdatedException(Exception exception) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ItineraryNotLoadedException.class)
    public ResponseEntity<ErrorMessage> itineraryNotLoadedException(Exception exception) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}