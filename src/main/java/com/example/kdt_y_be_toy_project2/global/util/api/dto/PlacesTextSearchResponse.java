package com.example.kdt_y_be_toy_project2.global.util.api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PlacesTextSearchResponse {
    private List<Place> results;
    private PlacesSearchStatus status;

}
