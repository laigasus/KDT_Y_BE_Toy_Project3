package com.example.kdt_y_be_toy_project2.global.util.api.controller;

import com.example.kdt_y_be_toy_project2.global.util.api.dto.PlacesTextSearchResponse;
import com.example.kdt_y_be_toy_project2.global.util.api.service.GoogleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class GoogleController {
    private final GoogleService googleService;
    @GetMapping("/search")
    public ResponseEntity<PlacesTextSearchResponse> find(
            @RequestParam(required = false)
            String keyword
    ){
        return ResponseEntity.ok(googleService.searchResponse(keyword));
    }
}
