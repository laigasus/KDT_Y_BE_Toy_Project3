package com.example.kdt_y_be_toy_project2.domain.itinerary.controller;

import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryRequest;
import com.example.kdt_y_be_toy_project2.domain.itinerary.dto.ItineraryResponse;
import com.example.kdt_y_be_toy_project2.domain.itinerary.service.ItineraryService;
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

import java.util.List;

@RestController
@RequestMapping("/itinerary")
@RequiredArgsConstructor
public class ItineraryController {

    private final ItineraryService itineraryService;

    // POST 여정 기록 다건
    @PostMapping("{id}")
    @Operation(summary = "여정 추가")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "여정 추가를 위한 값", required = true)
    @Parameter(
            name = "id",
            description = "추가하고자 하는 여정 일정의 ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "여정 일정 추가 성공", content = @Content(schema = @Schema(implementation = ItineraryResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    public ResponseEntity<?> addItineraries(@PathVariable long id, @RequestBody @Valid List<ItineraryRequest> itineraryRequestList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itineraryService.insertItineraries(id, itineraryRequestList));
    }

    // GET 여행 일정 조회 다건
    @GetMapping("")
    @Operation(summary = "모든 여정 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모든 여정 조회 성공", content = @Content(schema = @Schema(implementation = ItineraryResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    public ResponseEntity<?> bringItineraries() {
        return ResponseEntity.status(HttpStatus.OK).body(itineraryService.selectItineraries());
    }

    // GET 여행 일정 조회 단건 {id}
    @GetMapping("{id}")
    @Operation(summary = "특정 여정 조회")
    @Parameter(
            name = "id",
            description = "조회하고자 하는 여정 일정의 ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 여행 일정 조회 성공", content = @Content(schema = @Schema(implementation = ItineraryResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))
    })
    public ResponseEntity<?> bringItinerary(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(itineraryService.selectItinerary(id));
    }

    // PATCH 여정 정보 수정 {id}
    @PatchMapping("{id}")
    @Operation(summary = "하나의 여정 정보 수정")
    @Parameter(
            name = "id",
            description = "수정하고자 하는 여정 일정의 ID")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "여정 일정 수정을 위한 값", required = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "여행 일정 수정 성공", content = @Content(schema = @Schema(implementation = ItineraryResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    public ResponseEntity<?> editItinerary(@PathVariable long id, @RequestBody @Valid ItineraryRequest itineraryRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(itineraryService.updateItinerary(id, itineraryRequest));
    }
}
