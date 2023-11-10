package com.example.kdt_y_be_toy_project2.global.util.api.service;

import com.example.kdt_y_be_toy_project2.global.util.api.dto.Place;
import com.example.kdt_y_be_toy_project2.global.util.api.dto.PlaceDTO;
import com.example.kdt_y_be_toy_project2.global.util.api.dto.PlacesSearchStatus;
import com.example.kdt_y_be_toy_project2.global.util.api.dto.PlacesTextSearchResponse;
import com.example.kdt_y_be_toy_project2.global.util.api.repository.GoogleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GoogleService {

    private final GoogleRepository googleRepository;

    public PlacesTextSearchResponse searchResponse(String keyword) {
        List<Place> places = googleRepository.findByKeyword(keyword);
        if (places.isEmpty()) {
            throw new NotFoundException("검색결과 없음");
        }

        List<PlaceDTO> placeDTOs = places.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        PlacesTextSearchResponse response = new PlacesTextSearchResponse();
        response.setPlaces(placeDTOs);
        return response;
    }

    private PlaceDTO convertToDTO(Place place) {
        return PlaceDTO.fromPlace(place);
    }
}
