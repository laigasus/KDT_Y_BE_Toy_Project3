package com.example.kdt_y_be_toy_project2.domain.trip.controller;

import com.example.kdt_y_be_toy_project2.domain.trip.error.*;
import com.example.kdt_y_be_toy_project2.global.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TripControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(Exception exception) {
        ErrorMessage message = new ErrorMessage(
                "유효하지 않은 JSON 형태 입니다.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TripNotInsertedException.class})
    public ResponseEntity<ErrorMessage> tripNotInsertedException(Exception exception) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = TripNotLoadedException.class)
    public ResponseEntity<ErrorMessage> tripNotLoadedException(Exception exception) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TripNotUpdatedException.class)
    public ResponseEntity<ErrorMessage> tripNotUpdatedException(Exception exception) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotValidTripNameException.class)
    public ResponseEntity<ErrorMessage> notValidTripException(Exception exception) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TripServiceUnavailable.class)
    public ResponseEntity<ErrorMessage> tripServiceUnavailable(Exception exception) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = TripRequestTimeoutException.class)
    public ResponseEntity<ErrorMessage> tripRequestTimeoutException(Exception exception) {
        ErrorMessage message = new ErrorMessage(
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(message, HttpStatus.REQUEST_TIMEOUT);
    }
}
