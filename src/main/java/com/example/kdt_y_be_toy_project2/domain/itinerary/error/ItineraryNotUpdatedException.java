package com.example.kdt_y_be_toy_project2.domain.itinerary.error;
import com.example.kdt_y_be_toy_project2.global.exception.GlobalException;

public class ItineraryNotUpdatedException extends GlobalException {
    public ItineraryNotUpdatedException() {
        super("여정이 수정되지 않았습니다");
    }
}
