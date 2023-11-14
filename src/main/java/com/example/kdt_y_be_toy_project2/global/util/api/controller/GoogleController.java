package com.example.kdt_y_be_toy_project2.global.util.api.controller;

import com.example.kdt_y_be_toy_project2.global.util.api.dto.Place;
import com.example.kdt_y_be_toy_project2.global.util.api.service.GoogleMapUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GoogleController {

    @GetMapping("/places/search/{keyword}")
    public ResponseEntity<List<Place>> searchPlaces(@PathVariable String keyword) {
        List<Place> places = GoogleMapUtils.searchPlaces(keyword);
        return ResponseEntity.ok(places);
    }
}
