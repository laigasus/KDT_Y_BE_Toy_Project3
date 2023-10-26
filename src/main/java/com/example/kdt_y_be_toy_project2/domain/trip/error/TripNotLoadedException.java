package com.example.kdt_y_be_toy_project2.domain.trip.error;


public class TripNotLoadedException extends IllegalArgumentException {

    public TripNotLoadedException() {
        super("여행을 가져오지 못했습니다");
    }

}
