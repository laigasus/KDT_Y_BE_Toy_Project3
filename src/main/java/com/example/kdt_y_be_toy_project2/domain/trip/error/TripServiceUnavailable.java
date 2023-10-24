package com.example.kdt_y_be_toy_project2.domain.trip.error;

public class TripServiceUnavailable extends IllegalArgumentException {
    public TripServiceUnavailable(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return "현재 서버에서 서비스를 제공할 수 없습니다.";
    }
}
