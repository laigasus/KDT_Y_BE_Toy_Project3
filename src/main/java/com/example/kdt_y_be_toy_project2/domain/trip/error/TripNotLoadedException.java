package com.example.kdt_y_be_toy_project2.domain.trip.error;


public class TripNotLoadedException extends IllegalArgumentException {

    public TripNotLoadedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "여행을 가져오지 못했습니다";
    }
}
