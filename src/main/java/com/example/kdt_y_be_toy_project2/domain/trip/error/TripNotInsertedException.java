package com.example.kdt_y_be_toy_project2.domain.trip.error;


public class TripNotInsertedException extends IllegalArgumentException {
    public TripNotInsertedException() {
        super("여행을 저장하는데 실패하였습니다");

    }

}
