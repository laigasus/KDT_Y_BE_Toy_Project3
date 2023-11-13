package com.example.kdt_y_be_toy_project2.global.util.api.dto;

import lombok.Data;

@Data
public class PlaceDTO {
    private String formattedAddress;
    //private String internationalPhoneNumber;
    private String name;


    public static PlaceDTO fromPlace(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setFormattedAddress(place.getFormatted_address());
       // placeDTO.setInternationalPhoneNumber(place.getInternational_phone_number());
        placeDTO.setName(place.getName());


        return placeDTO;
    }
}
