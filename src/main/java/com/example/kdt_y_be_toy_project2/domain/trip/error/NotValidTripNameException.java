package com.example.kdt_y_be_toy_project2.domain.trip.error;

public class NotValidTripNameException extends IllegalArgumentException {

    public NotValidTripNameException() {
        super("올바른 여행지가 아닙니다");
    }

}
