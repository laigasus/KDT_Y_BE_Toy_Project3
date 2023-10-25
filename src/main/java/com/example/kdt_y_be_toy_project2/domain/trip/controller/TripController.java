package com.example.kdt_y_be_toy_project2.domain.trip.controller;

import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripRequest;
import com.example.kdt_y_be_toy_project2.domain.trip.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    // POST
    // 여행 일정 기록
    // BODY
    @PostMapping("")
    public ResponseEntity<?> addTrip(@RequestBody @Valid TripRequest tripRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tripService.insertTrip(tripRequest));
    }

    // GET
    // 여행 일정 전체조회
    @GetMapping("")
    public ResponseEntity<?> bringAllTrips() {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.selectTrips());
    }

    // GET
    // 여행 일정 조회 단건 {id}
    @GetMapping("/{id}")
    public ResponseEntity<?> bringTripById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.selectTrip(id));
    }

    // PATCH
    // 여행 정보 수정 {id}
    // Body
    @PatchMapping("/{id}")
    public ResponseEntity<?> editTrip(@PathVariable long id,
                                      @RequestBody @Valid TripRequest tripRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.updateTrip(id, tripRequest));
    }
}
