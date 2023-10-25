package com.example.kdt_y_be_toy_project2.domain.itinerary.error;

import com.example.kdt_y_be_toy_project2.global.exception.GlobalException;

public class ItineraryNotInsertedException extends GlobalException {
    public ItineraryNotInsertedException() {
        super("여정이 저장되지 않았습니다");
    }
}