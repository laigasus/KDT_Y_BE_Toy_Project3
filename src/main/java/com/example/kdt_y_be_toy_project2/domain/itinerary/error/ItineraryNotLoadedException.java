package com.example.kdt_y_be_toy_project2.domain.itinerary.error;

import com.example.kdt_y_be_toy_project2.global.exception.GlobalException;

public class ItineraryNotLoadedException extends GlobalException {
    public ItineraryNotLoadedException() {
        super("여정을 가져오지 못했습니다");
    }
}
