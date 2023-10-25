package com.example.kdt_y_be_toy_project2.domain.itinerary.controller;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryRequest;
import com.example.kdt_y_be_toy_project2.domain.itinerary.service.ItineraryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("itinerary")
@RequiredArgsConstructor
public class ItineraryController {

    private final ItineraryService itineraryService;

    // POST 여정 기록 다건
    @PostMapping("{id}")
    public ResponseEntity<?> addItineraries(@PathVariable long id, @RequestBody @Valid List<ItineraryRequest> itineraryRequestList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itineraryService.insertItineraries(id, itineraryRequestList));
    }

    // GET 여행 일정 조회 다건
    @GetMapping
    public ResponseEntity<?> bringItineraries() {
        return ResponseEntity.status(HttpStatus.OK).body(itineraryService.selectItineraries());
    }

    // GET 여행 일정 조회 단건 {id}
    @GetMapping("{id}")
    public ResponseEntity<?> bringItinerary(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(itineraryService.selectItinerary(id));
    }

    // PATCH 여정 정보 수정 {id}
    @PatchMapping("{id}")
    public ResponseEntity<?> editItinerary(@PathVariable long id, @RequestBody @Valid ItineraryRequest itineraryRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(itineraryService.updateItinerary(id, itineraryRequest));
    }
}
