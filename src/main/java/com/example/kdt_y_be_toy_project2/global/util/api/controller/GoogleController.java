package com.example.kdt_y_be_toy_project2.global.util.api.controller;

import com.example.kdt_y_be_toy_project2.global.util.api.dto.Place;
import com.example.kdt_y_be_toy_project2.global.util.api.service.GoogleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GoogleController {

    private final GoogleService googleService;

    @GetMapping("/places/search")
    public ResponseEntity<List<Place>> searchPlaces(@RequestParam String keyword) {
        List<Place> places = googleService.searchPlaces(keyword);
        return ResponseEntity.ok(places);
    }
}
