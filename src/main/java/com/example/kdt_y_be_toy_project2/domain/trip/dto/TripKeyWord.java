package com.example.kdt_y_be_toy_project2.domain.trip.dto;

public record TripKeyWord(
        String keyWord
) {
    public TripKeyWord {
        if (keyWord.isBlank()) {
            throw new IllegalArgumentException("검색어는 공백이 될 수 없습니다");
        }
    }
}