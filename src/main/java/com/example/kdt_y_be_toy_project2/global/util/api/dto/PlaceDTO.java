package com.example.kdt_y_be_toy_project2.global.util.api.dto;

import lombok.Setter;

@Setter
public class PlaceDTO {
    private String formattedAddress;
    private String name;


    public static PlaceDTO fromPlace(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setFormattedAddress(place.getFormattedAddress());
        placeDTO.setName(place.getName());


        return placeDTO;
    }
}
