package com.example.kdt_y_be_toy_project2.domain.itinerary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransportEnum {
    BUS("버스"),
    SUBWAY("지하철"),
    KTX("KTX"),
    PLANE("비행기"),
    CAR("자동차"),
    WALK("도보"),
    BIKE("자전거"),
    SHIP("배");

    private final String method;
}
