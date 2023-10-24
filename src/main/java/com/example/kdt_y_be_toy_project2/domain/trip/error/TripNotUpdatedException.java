package com.example.kdt_y_be_toy_project2.domain.trip.error;


public class TripNotUpdatedException extends IllegalArgumentException {
    public TripNotUpdatedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "여행이 변경되지 않았습니다";
    }
}
