package com.example.kdt_y_be_toy_project2.global.util.api.dto;

import lombok.Data;

@Data
public class PlaceDTO {
    private String adrAddress;
    private String formattedAddress;
    private String formattedPhoneNumber;
    private String name;


    public static PlaceDTO fromPlace(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setAdrAddress(place.getAdr_address());
        placeDTO.setFormattedAddress(place.getFormatted_address());
        placeDTO.setFormattedPhoneNumber(place.getFormatted_phone_number());
        placeDTO.setName(place.getName());


        return placeDTO;
    }
}
