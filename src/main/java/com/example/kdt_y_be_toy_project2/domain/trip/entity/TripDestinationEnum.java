package com.example.kdt_y_be_toy_project2.domain.trip.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TripDestinationEnum {
    DOMESTIC("국내"), INTERNATIONAL("국외");

    private final String method;
}
