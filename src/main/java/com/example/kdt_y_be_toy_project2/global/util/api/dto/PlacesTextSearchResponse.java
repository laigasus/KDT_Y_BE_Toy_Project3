package com.example.kdt_y_be_toy_project2.global.util.api.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlacesTextSearchResponse {

    public List<Place> places = new ArrayList<>();

    public List<String> html_attributions;
    public List<Place> results;
    public PlacesSearchStatus status;

    public void setPlaces(List<PlaceDTO> placeDTOs) {
    }
}
