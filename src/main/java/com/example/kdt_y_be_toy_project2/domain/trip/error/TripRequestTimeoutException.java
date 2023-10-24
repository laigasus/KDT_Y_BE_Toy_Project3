package com.example.kdt_y_be_toy_project2.domain.trip.error;

public class TripRequestTimeoutException extends IllegalArgumentException{
    public TripRequestTimeoutException(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return "요청 시간이 초과되었습니다.";
    }
}
