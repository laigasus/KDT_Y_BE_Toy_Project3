package com.example.kdt_y_be_toy_project2.global.util.api.service;

import com.example.kdt_y_be_toy_project2.global.util.api.dto.Place;
import com.example.kdt_y_be_toy_project2.global.util.api.dto.PlacesSearchStatus;
import com.example.kdt_y_be_toy_project2.global.util.api.dto.PlacesTextSearchResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GoogleMapUtils {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("GOOGLE_MAP_API_KEY");


    public static Place searchPlaces(String keyword) {
        if (API_KEY.isBlank()) {
            throw new IllegalStateException("API키가 문제임");
        }
        String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" +
                keyword + "&key=" + API_KEY;
        try {
            PlacesTextSearchResponse response = restTemplate.getForObject(url, PlacesTextSearchResponse.class);

            if (response != null && response.getStatus().equals(PlacesSearchStatus.OK)) {
                List<Place> places = response.getResults();
                if (!places.isEmpty()) {
                    return places.get(0);
                } else {
                    throw new IllegalStateException("검색 결과가 없습니다.");
                }
            } else {
                throw new IllegalStateException("API 에러: " + response);
            }
        } catch (Exception e) {
            throw new IllegalStateException("API 호출 중 오류 발생", e);
        }
    }
}