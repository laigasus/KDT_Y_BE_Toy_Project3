package com.example.kdt_y_be_toy_project2.domain.itinerary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransportationEnum {
    BUS("버스"), SUBWAY("지하철"), KTX("KTX"), PLANE("비행기");

    private final String method;
}
