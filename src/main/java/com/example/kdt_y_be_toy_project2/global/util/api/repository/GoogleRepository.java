package com.example.kdt_y_be_toy_project2.global.util.api.repository;

import com.example.kdt_y_be_toy_project2.global.util.api.dto.Place;
import com.example.kdt_y_be_toy_project2.global.util.api.dto.PlacesTextSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Repository
@AllArgsConstructor

public class GoogleRepository {
    private static final String API_KEY = "AIzaSyBchN_HnNM60sMXwGQFiByLkaiPjoZBeAY";
    private final RestTemplate restTemplate;

    private final String API_URL =  "https://maps.googleapis.com/maps/api/place/textsearch/json?&radius=2000&key=" + API_KEY;

    public List<Place> findByKeyword(String keyword){
        URI uri = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("query", keyword)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", API_KEY);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");

        RequestEntity<String> request = new RequestEntity<>(httpHeaders, HttpMethod.GET, uri);

        return restTemplate.exchange(request, PlacesTextSearchResponse.class).getBody().getPlaces();
    }
}
