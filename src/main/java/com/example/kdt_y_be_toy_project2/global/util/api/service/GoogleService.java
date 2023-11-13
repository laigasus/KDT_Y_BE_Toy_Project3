package com.example.kdt_y_be_toy_project2.global.util.api.service;

import com.example.kdt_y_be_toy_project2.global.util.api.dto.Place;
import com.example.kdt_y_be_toy_project2.global.util.api.dto.PlacesSearchStatus;
import com.example.kdt_y_be_toy_project2.global.util.api.dto.PlacesTextSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class GoogleService {

    @Value("${spring.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    private static final String GoogleMapURL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";

    public GoogleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Place> searchPlaces(String keyword) {
        String url = GoogleMapURL + keyword +
                "&key=" + apiKey;

        PlacesTextSearchResponse response = restTemplate.getForObject(url, PlacesTextSearchResponse.class);

        if (response != null && response.getStatus().equals(PlacesSearchStatus.OK)) {
            return response.getResults();
        } else {
            return Collections.emptyList();
        }
    }
}
