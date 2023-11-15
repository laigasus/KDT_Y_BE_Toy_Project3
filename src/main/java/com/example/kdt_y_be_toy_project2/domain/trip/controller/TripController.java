package com.example.kdt_y_be_toy_project2.domain.trip.controller;

import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripKeyWord;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripRequest;
import com.example.kdt_y_be_toy_project2.domain.trip.dto.TripResponse;
import com.example.kdt_y_be_toy_project2.domain.trip.service.TripService;
import com.example.kdt_y_be_toy_project2.global.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "여행 일정 추가")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "여행을 추가를 위한 값", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "여행 일정 추가 성공", content = @Content(schema = @Schema(implementation = TripResponse.TripInfo.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    public ResponseEntity<?> addTrip(@RequestBody @Valid TripRequest tripRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tripService.insertTrip(tripRequest));
    }

    // GET
    // 여행 일정 전체조회
    @GetMapping("")
    @Operation(summary = "모든 여행 일정 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모든 여행 일정 조회 성공", content = @Content(schema = @Schema(implementation = TripResponse.TripInfo.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    public ResponseEntity<?> bringAllTrips() {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.selectTrips());
    }

    // GET
    // 여행 일정 조회 단건 {id}
    @GetMapping("/{id}")
    @Operation(summary = "특정 여행 일정 조회")
    @Parameter(
            name = "id",
            description = "조회하고자 하는 여행 일정의 ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 여행 일정 조회 성공", content = @Content(schema = @Schema(implementation = TripResponse.TripInfo.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<?> bringTripById(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.selectTrip(id));
    }

    // PATCH
    // 여행 정보 수정 {id}
    // Body
    @PatchMapping("/{id}")
    @Operation(summary = "여행 일정 수정")
    @Parameter(
            name = "id",
            description = "수정하고자 하는 여행 일정의 ID")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "여행 일정 수정을 위한 값", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "여행 일정 수정 성공", content = @Content(schema = @Schema(implementation = TripResponse.TripInfo.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    public ResponseEntity<?> editTrip(@PathVariable long id,
                                      @RequestBody @Valid TripRequest tripRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.updateTrip(id, tripRequest));
    }

    @GetMapping("/get")
    @Operation(summary = "검색어에 맞는 여행 정보 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "여행 조회 성공", content = @Content(schema = @Schema(implementation = TripResponse.TripByKeyWord.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<?> bringTripById(@RequestParam("keyWord") @Valid String keyWord) {
        return ResponseEntity.status(HttpStatus.OK).body(tripService.selectTrips(keyWord));
    }
}
