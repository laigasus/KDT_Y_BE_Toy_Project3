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

    @PostMapping("")
    public ResponseEntity<?> addTrip(@RequestBody @Valid TripRequest tripRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tripService.insertTrip(tripRequest));
    }

    @GetMapping("")
    public ResponseEntity<?> bringAllTrips() {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.selectTrips());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> bringTripById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.selectTrip(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editTrip(@PathVariable long id,
                                      @RequestBody @Valid TripRequest tripRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.updateTrip(id, tripRequest));
    }

    @GetMapping("/get")
    public ResponseEntity<?> bringTripById(@RequestParam("keyWord") @Valid String keyWord) {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.selectTrips(keyWord));
    }
}
