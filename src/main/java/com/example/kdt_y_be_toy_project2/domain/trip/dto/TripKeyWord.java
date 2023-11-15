package com.example.kdt_y_be_toy_project2.domain.trip.dto;

import jakarta.validation.constraints.NotBlank;

public record TripKeyWord(
        String keyWord
) {
        public TripKeyWord{
                if(keyWord.isBlank() || keyWord.isEmpty() || keyWord.startsWith(" ")){
                        throw new RuntimeException("O M G");
                }
        }
}