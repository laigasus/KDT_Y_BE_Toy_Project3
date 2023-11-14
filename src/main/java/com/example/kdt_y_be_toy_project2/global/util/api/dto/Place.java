package com.example.kdt_y_be_toy_project2.global.util.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Place {
        @JsonProperty("formatted_address")
        String formattedAddress;
        //String international_phone_number;
        String name;

}

